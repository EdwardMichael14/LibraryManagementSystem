import React, { useState, useEffect } from 'react';
import { useSelector, useDispatch } from 'react-redux';
import { useNavigate } from 'react-router-dom';
import { logout } from '../redux/authSlice';
import api from '../api/axiosConfig';

const AdminDashboard = () => {
  const { user } = useSelector((state) => state.auth);
  const dispatch = useDispatch();
  const navigate = useNavigate();

  const [stats, setStats] = useState({
    totalBooks: 0,
    totalUsers: 0
  });
  const [books, setBooks] = useState([]);
  const [users, setUsers] = useState([]);
  const [borrowedBooks, setBorrowedBooks] = useState([]);
  const [loading, setLoading] = useState(true);
  const [activeTab, setActiveTab] = useState('overview');

  const [newBook, setNewBook] = useState({
    title: '',
    author: '',
    isbn: '',
    edition: '',
    noOfCopies: 1
  });
  const [addBookStatus, setAddBookStatus] = useState({ loading: false, error: null, success: null });

  const [newUser, setNewUser] = useState({
    fullName: '',
    email: '',
    phone: '',
    userName: '',
    password: ''
  });
  const [registerUserStatus, setRegisterUserStatus] = useState({ loading: false, error: null, success: null });

  useEffect(() => {
    const fetchData = async () => {
      try {
        setLoading(true);

        const [booksRes, usersRes, borrowedRes] = await Promise.all([
          api.get('/admin/all-books'),
          api.get('/admin/view-users'),
          api.get('/admin/borrowed-books')
        ]);

        const booksData = booksRes.data.data || [];
        const usersData = usersRes.data.data || [];
        const borrowedData = borrowedRes.data.data || [];

        setBooks(booksData);
        setUsers(usersData);
        setBorrowedBooks(borrowedData);
        setStats({
          totalBooks: booksData.length,
          totalUsers: usersData.length
        });
      } catch (err) {
        console.error("Failed to fetch admin data:", err);
      } finally {
        setLoading(false);
      }
    };

    fetchData();
  }, []);

  const handleLogout = () => {
    dispatch(logout());
    navigate('/login');
  };

  const getBookTitle = (bookId) => {
    const book = books.find(b => b.id === bookId);
    return book ? book.title : 'Unknown Book';
  };

  const handleAddBookChange = (e) => {
    setNewBook({
      ...newBook,
      [e.target.name]: e.target.value
    });
  };

  const handleAddBookSubmit = async (e) => {
    e.preventDefault();
    setAddBookStatus({ loading: true, error: null, success: null });

    try {
      const payload = {
        book: {...newBook, noOfCopies: parseInt(newBook.noOfCopies)
        }
      };

      await api.post('/admin/add-book', payload);
      
      setAddBookStatus({ loading: false, error: null, success: 'Great job! The book has been added to the library.' });
      setNewBook({ title: '', author: '', isbn: '', edition: '', noOfCopies: 1 });
    
      const booksRes = await api.get('/admin/all-books');
      setBooks(booksRes.data.data || []);
      setStats(prev => ({ ...prev, totalBooks: (booksRes.data.data || []).length }));

    } catch (err) {
      console.error("Error adding book:", err);
      setAddBookStatus({ 
        loading: false, 
        error: err.response?.data?.message || 'Failed to add book', 
        success: null 
      });
    }
  };

  const handleRegisterUserChange = (e) => {
    setNewUser({
      ...newUser,
      [e.target.name]: e.target.value
    });
  };

  const handleRegisterUserSubmit = async (e) => {
    e.preventDefault();
    setRegisterUserStatus({ loading: true, error: null, success: null });

    try {
      await api.post('/admin/register-user', newUser);
      
      setRegisterUserStatus({ loading: false, error: null, success: 'New member registered successfully!' });
      setNewUser({ fullName: '', email: '', phone: '', userName: '', password: '' });
      
      const usersRes = await api.get('/admin/view-users');
      setUsers(usersRes.data.data || []);
      setStats(prev => ({ ...prev, totalUsers: (usersRes.data.data || []).length }));

    } catch (err) {
      console.error("Error registering user:", err);
      setRegisterUserStatus({ 
        loading: false, 
        error: err.response?.data?.message || 'Failed to register user', 
        success: null 
      });
    }
  };

  return (
    <div className="min-h-screen bg-stone-50">
      {/* Librarian Header */}
      <nav className="bg-amber-800 shadow-md">
        <div className="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8">
          <div className="flex justify-between h-20">
            <div className="flex items-center">
              <span className="text-2xl mr-3">🧐</span>
              <div>
                <h1 className="text-xl font-bold text-white font-serif">Librarian's Desk</h1>
                <p className="text-xs text-amber-200">Manage your collection with care</p>
              </div>
            </div>
            <div className="flex items-center">
              <span className="mr-4 text-amber-100 text-sm hidden sm:block">You are logged in as Admin</span>
              <button
                onClick={handleLogout}
                className="bg-amber-900 hover:bg-red-700 text-white px-4 py-2 rounded-lg text-sm font-medium transition-colors border border-amber-700"
              >
                Sign Out
              </button>
            </div>
          </div>
        </div>
      </nav>

      <main className="max-w-7xl mx-auto py-10 px-4 sm:px-6 lg:px-8">
        
        {/* Navigation Tabs */}
        <div className="mb-8">
          <div className="flex space-x-2 bg-white p-1 rounded-xl shadow-sm border border-stone-200 overflow-x-auto">
            {['overview', 'books', 'users', 'add-book', 'register-user'].map((tab) => (
              <button
                key={tab}
                onClick={() => setActiveTab(tab)}
                className={`
                  flex-1 py-3 px-4 rounded-lg font-medium text-sm capitalize transition-all duration-200 whitespace-nowrap
                  ${activeTab === tab 
                    ? 'bg-amber-100 text-amber-900 shadow-sm' 
                    : 'text-gray-500 hover:bg-gray-50 hover:text-gray-700'}
                `}
              >
                {tab === 'add-book' ? '+ Add New Book' : tab === 'register-user' ? '+ Register User' : tab === 'borrowed' ? 'Borrowed Books' : tab}
              </button>
            ))}
          </div>
        </div>

        {/* Content Area */}
        {loading ? (
           <div className="text-center py-20">
             <div className="text-4xl mb-4">📚</div>
             <p className="text-gray-500 animate-pulse">Checking the shelves...</p>
           </div>
        ) : (
          <>
            {/* OVERVIEW TAB */}
            {activeTab === 'overview' && (
              <div className="grid grid-cols-1 md:grid-cols-2 gap-8">
                {/* Stats Card */}
                <div className="bg-white rounded-xl shadow-sm border border-stone-100 p-6">
                  <h3 className="text-lg font-bold text-gray-800 mb-4 font-serif">Library Statistics</h3>
                  <div className="grid grid-cols-2 gap-4">
                    <div className="bg-blue-50 p-6 rounded-xl text-center border border-blue-100">
                      <p className="text-4xl font-bold text-blue-600 mb-1">{stats.totalBooks}</p>
                      <p className="text-sm text-blue-800 font-medium">Books in Collection</p>
                    </div>
                    <div className="bg-green-50 p-6 rounded-xl text-center border border-green-100">
                      <p className="text-4xl font-bold text-green-600 mb-1">{stats.totalUsers}</p>
                      <p className="text-sm text-green-800 font-medium">Registered Readers</p>
                    </div>
                  </div>
                </div>
                
                {/* Quick Actions Card */}
                <div className="bg-white rounded-xl shadow-sm border border-stone-100 p-6 flex flex-col justify-center">
                    <h3 className="text-lg font-bold text-gray-800 mb-4 font-serif">What would you like to do?</h3>
                    <div className="space-y-3">
                        <button 
                            onClick={() => setActiveTab('add-book')}
                            className="w-full bg-amber-600 text-white px-6 py-4 rounded-xl hover:bg-amber-700 transition flex items-center justify-center font-bold shadow-sm"
                        >
                            <span className="mr-2">📖</span> Add a New Book to Library
                        </button>
                        <button 
                            onClick={() => setActiveTab('users')}
                            className="w-full bg-white border-2 border-gray-200 text-gray-600 px-6 py-4 rounded-xl hover:bg-gray-50 transition flex items-center justify-center font-bold"
                        >
                            <span className="mr-2">👥</span> View Member List
                        </button>
                    </div>
                </div>
              </div>
            )}

            {/* BOOKS TAB */}
            {activeTab === 'books' && (
              <div className="bg-white shadow-sm rounded-xl border border-stone-200 overflow-hidden">
                <div className="px-6 py-5 border-b border-gray-100 bg-stone-50">
                  <h3 className="text-lg font-bold text-gray-800 font-serif">Current Book Inventory</h3>
                  <p className="text-sm text-gray-500">All the books currently recorded in the system.</p>
                </div>
                <ul className="divide-y divide-gray-100">
                  {books.length === 0 ? (
                    <li className="px-6 py-10 text-center text-gray-400">
                      <p className="text-xl mb-2">🕸️</p>
                      No books found. Go to "Add New Book" to start your collection!
                    </li>
                  ) : (
                    books.map((book) => (
                      <li key={book.id || book.isbn} className="px-6 py-4 hover:bg-stone-50 transition-colors">
                        <div className="flex items-center justify-between">
                          <div>
                            <p className="text-lg font-semibold text-gray-800 font-serif">{book.title}</p>
                            <p className="text-sm text-gray-600">by <span className="font-medium text-amber-700">{book.author}</span></p>
                            <p className="text-xs text-gray-400 mt-1">ISBN: {book.isbn}</p>
                          </div>
                          <div className="text-right">
                             <div className="mb-1">
                                <span className="text-sm text-gray-500 font-medium">Copies: {book.noOfCopies}</span>
                             </div>
                             <span className={`px-3 py-1 inline-flex text-xs leading-5 font-semibold rounded-full ${
                                book.status === 'AVAILABLE' ? 'bg-green-100 text-green-800' : 'bg-red-100 text-red-800'
                             }`}>
                                {book.status}
                             </span>
                          </div>
                        </div>
                      </li>
                    ))
                  )}
                </ul>
              </div>
            )}

            {/* USERS TAB */}
            {activeTab === 'users' && (
              <div className="bg-white shadow-sm rounded-xl border border-stone-200 overflow-hidden">
                 <div className="px-6 py-5 border-b border-gray-100 bg-stone-50">
                  <h3 className="text-lg font-bold text-gray-800 font-serif">Registered Readers</h3>
                  <p className="text-sm text-gray-500">People who have signed up for a library card.</p>
                </div>
                <ul className="divide-y divide-gray-100">
                  {users.length === 0 ? (
                    <li className="px-6 py-10 text-center text-gray-400">
                      <p className="text-xl mb-2">🦗</p>
                      No users found yet.
                    </li>
                  ) : (
                    users.map((u) => (
                      <li key={u.id} className="px-6 py-4 hover:bg-stone-50 transition-colors">
                        <div className="flex items-center justify-between">
                          <div className="flex items-center">
                            <div className="h-10 w-10 rounded-full bg-amber-100 flex items-center justify-center text-amber-700 font-bold mr-4">
                                {u.fullName ? u.fullName.charAt(0).toUpperCase() : '?'}
                            </div>
                            <div>
                                <p className="text-sm font-bold text-gray-900">{u.fullName}</p>
                                <p className="text-sm text-gray-500">{u.email}</p>
                            </div>
                          </div>
                          <div>
                              <span className="text-xs bg-gray-100 text-gray-600 px-2 py-1 rounded">Member</span>
                          </div>
                        </div>
                      </li>
                    ))
                  )}
                </ul>
              </div>
            )}

            {/* BORROWED BOOKS TAB */}
            {activeTab === 'borrowed' && (
              <div className="bg-white shadow-sm rounded-xl border border-stone-200 overflow-hidden">
                <div className="px-6 py-5 border-b border-gray-100 bg-stone-50">
                  <h3 className="text-lg font-bold text-gray-800 font-serif">Currently Borrowed Books</h3>
                  <p className="text-sm text-gray-500">List of books currently out with readers.</p>
                </div>
                <ul className="divide-y divide-gray-100">
                  {borrowedBooks.length === 0 ? (
                    <li className="px-6 py-10 text-center text-gray-400">
                      <p className="text-xl mb-2">✅</p>
                      All books are currently in the library.
                    </li>
                  ) : (
                    borrowedBooks.map((record) => (
                      <li key={record.id} className="px-6 py-4 hover:bg-stone-50 transition-colors">
                        <div className="flex items-center justify-between">
                          <div>
                            <p className="text-lg font-semibold text-gray-800 font-serif">{getBookTitle(record.bookId)}</p>
                            <p className="text-sm text-gray-600">Borrowed by: <span className="font-medium text-amber-700">{record.email}</span></p>
                          </div>
                          <div className="text-right">
                             <p className="text-xs text-gray-400">Date Borrowed</p>
                             <p className="text-sm font-medium text-gray-700">{new Date(record.borrowDate).toLocaleDateString()}</p>
                          </div>
                        </div>
                      </li>
                    ))
                  )}
                </ul>
              </div>
            )}

            {/* ADD BOOK TAB */}
            {activeTab === 'add-book' && (
              <div className="bg-white shadow-lg rounded-xl border border-stone-200 p-8 max-w-2xl mx-auto">
                <div className="text-center mb-8">
                    <h3 className="text-2xl font-bold text-gray-800 font-serif mb-2">Add a New Book</h3>
                    <p className="text-gray-500">Fill in the details below to add a new title to the shelves.</p>
                </div>
                
                {addBookStatus.success && (
                    <div className="mb-6 bg-green-50 border border-green-200 text-green-700 px-4 py-4 rounded-lg flex items-center">
                        <span className="text-2xl mr-2">✅</span> {addBookStatus.success}
                    </div>
                )}
                {addBookStatus.error && (
                    <div className="mb-6 bg-red-50 border border-red-200 text-red-700 px-4 py-4 rounded-lg flex items-center">
                        <span className="text-2xl mr-2">⚠️</span> {addBookStatus.error}
                    </div>
                )}

                <form onSubmit={handleAddBookSubmit} className="space-y-6">
                    <div>
                        <label className="block text-sm font-bold text-gray-700 mb-1">Book Title</label>
                        <input
                            type="text"
                            name="title"
                            value={newBook.title}
                            onChange={handleAddBookChange}
                            className="w-full px-4 py-3 bg-gray-50 border border-gray-200 rounded-lg focus:outline-none focus:ring-2 focus:ring-amber-500"
                            placeholder="e.g. The Great Gatsby"
                            required
                        />
                    </div>
                    <div className="grid grid-cols-2 gap-4">
                        <div>
                            <label className="block text-sm font-bold text-gray-700 mb-1">Author</label>
                            <input
                                type="text"
                                name="author"
                                value={newBook.author}
                                onChange={handleAddBookChange}
                                className="w-full px-4 py-3 bg-gray-50 border border-gray-200 rounded-lg focus:outline-none focus:ring-2 focus:ring-amber-500"
                                placeholder="e.g. F. Scott Fitzgerald"
                                required
                            />
                        </div>
                         <div>
                            <label className="block text-sm font-bold text-gray-700 mb-1">Edition</label>
                            <input
                                type="text"
                                name="edition"
                                value={newBook.edition}
                                onChange={handleAddBookChange}
                                className="w-full px-4 py-3 bg-gray-50 border border-gray-200 rounded-lg focus:outline-none focus:ring-2 focus:ring-amber-500"
                                placeholder="e.g. 1st Edition"
                                required
                            />
                        </div>
                    </div>
                    <div className="grid grid-cols-2 gap-4">
                        <div>
                            <label className="block text-sm font-bold text-gray-700 mb-1">ISBN</label>
                            <input
                                type="text"
                                name="isbn"
                                value={newBook.isbn}
                                onChange={handleAddBookChange}
                                className="w-full px-4 py-3 bg-gray-50 border border-gray-200 rounded-lg focus:outline-none focus:ring-2 focus:ring-amber-500"
                                placeholder="e.g. 978-3-16-148410-0"
                                required
                            />
                        </div>
                        <div>
                            <label className="block text-sm font-bold text-gray-700 mb-1">Number of Copies</label>
                            <input
                                type="number"
                                name="noOfCopies"
                                min="1"
                                value={newBook.noOfCopies}
                                onChange={handleAddBookChange}
                                className="w-full px-4 py-3 bg-gray-50 border border-gray-200 rounded-lg focus:outline-none focus:ring-2 focus:ring-amber-500"
                                required
                            />
                        </div>
                    </div>
                    
                    <button
                        type="submit"
                        disabled={addBookStatus.loading}
                        className={`w-full py-3 px-4 rounded-lg text-white font-bold text-lg shadow-md transition-all
                            ${addBookStatus.loading 
                            ? 'bg-amber-300 cursor-not-allowed' 
                            : 'bg-amber-600 hover:bg-amber-700 hover:shadow-lg transform hover:-translate-y-0.5'
                            }`}
                    >
                        {addBookStatus.loading ? 'Adding to Shelf...' : 'Add Book to Library'}
                    </button>
                </form>
              </div>
            )}

            {/* REGISTER USER TAB */}
            {activeTab === 'register-user' && (
              <div className="bg-white shadow-lg rounded-xl border border-stone-200 p-8 max-w-2xl mx-auto">
                <div className="text-center mb-8">
                    <h3 className="text-2xl font-bold text-gray-800 font-serif mb-2">Register New Reader</h3>
                    <p className="text-gray-500">Create a new library card for a member.</p>
                </div>
                
                {registerUserStatus.success && (
                    <div className="mb-6 bg-green-50 border border-green-200 text-green-700 px-4 py-4 rounded-lg flex items-center">
                        <span className="text-2xl mr-2">✅</span> {registerUserStatus.success}
                    </div>
                )}
                {registerUserStatus.error && (
                    <div className="mb-6 bg-red-50 border border-red-200 text-red-700 px-4 py-4 rounded-lg flex items-center">
                        <span className="text-2xl mr-2">⚠️</span> {registerUserStatus.error}
                    </div>
                )}

                <form onSubmit={handleRegisterUserSubmit} className="space-y-6">
                    <div>
                        <label className="block text-sm font-bold text-gray-700 mb-1">Full Name</label>
                        <input
                            type="text"
                            name="fullName"
                            value={newUser.fullName}
                            onChange={handleRegisterUserChange}
                            className="w-full px-4 py-3 bg-gray-50 border border-gray-200 rounded-lg focus:outline-none focus:ring-2 focus:ring-amber-500"
                            placeholder="e.g. Jane Doe"
                            required
                        />
                    </div>
                    <div>
                        <label className="block text-sm font-bold text-gray-700 mb-1">Email Address</label>
                        <input
                            type="email"
                            name="email"
                            value={newUser.email}
                            onChange={handleRegisterUserChange}
                            className="w-full px-4 py-3 bg-gray-50 border border-gray-200 rounded-lg focus:outline-none focus:ring-2 focus:ring-amber-500"
                            placeholder="e.g. jane@example.com"
                            required
                        />
                    </div>
                     <div className="grid grid-cols-2 gap-4">
                        <div>
                            <label className="block text-sm font-bold text-gray-700 mb-1">Phone Number</label>
                            <input
                                type="tel"
                                name="phone"
                                value={newUser.phone}
                                onChange={handleRegisterUserChange}
                                className="w-full px-4 py-3 bg-gray-50 border border-gray-200 rounded-lg focus:outline-none focus:ring-2 focus:ring-amber-500"
                                placeholder="e.g. +1234567890"
                                required
                            />
                        </div>
                        <div>
                            <label className="block text-sm font-bold text-gray-700 mb-1">Username</label>
                            <input
                                type="text"
                                name="userName"
                                value={newUser.userName}
                                onChange={handleRegisterUserChange}
                                className="w-full px-4 py-3 bg-gray-50 border border-gray-200 rounded-lg focus:outline-none focus:ring-2 focus:ring-amber-500"
                                placeholder="e.g. janereads"
                                required
                            />
                        </div>
                    </div>
                    <div>
                        <label className="block text-sm font-bold text-gray-700 mb-1">Password</label>
                        <input
                            type="password"
                            name="password"
                            value={newUser.password}
                            onChange={handleRegisterUserChange}
                            className="w-full px-4 py-3 bg-gray-50 border border-gray-200 rounded-lg focus:outline-none focus:ring-2 focus:ring-amber-500"
                            placeholder="••••••••"
                            required
                        />
                    </div>
                    
                    <button
                        type="submit"
                        disabled={registerUserStatus.loading}
                        className={`w-full py-3 px-4 rounded-lg text-white font-bold text-lg shadow-md transition-all
                            ${registerUserStatus.loading 
                            ? 'bg-amber-300 cursor-not-allowed' 
                            : 'bg-amber-600 hover:bg-amber-700 hover:shadow-lg transform hover:-translate-y-0.5'
                            }`}
                    >
                        {registerUserStatus.loading ? 'Registering...' : 'Create Library Card'}
                    </button>
                </form>
              </div>
            )}

          </>
        )}
      </main>
    </div>
  );
};

export default AdminDashboard;
