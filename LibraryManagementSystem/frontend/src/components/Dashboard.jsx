import React, { useState, useEffect } from 'react';
import { useSelector, useDispatch } from 'react-redux';
import { useNavigate } from 'react-router-dom';
import { logout } from '../redux/authSlice';
import api from '../api/axiosConfig';

const Dashboard = () => {
  const { user } = useSelector((state) => state.auth);
  const dispatch = useDispatch();
  const navigate = useNavigate();

  const [books, setBooks] = useState([]);
  const [borrowedBooks, setBorrowedBooks] = useState([]);
  const [loading, setLoading] = useState(true);
  const [activeTab, setActiveTab] = useState('all-books');

  useEffect(() => {
    const fetchData = async () => {
      try {
        setLoading(true);
        const [allBooksRes, borrowedRes] = await Promise.all([
            api.get('/user/view-books'),
            api.get('/user/view-borrowed-books')
        ]);

        setBooks(allBooksRes.data.data || []);
        setBorrowedBooks(borrowedRes.data.data || []);

      } catch (err) {
        console.error("Failed to fetch user data:", err);
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

  const handleBorrow = async (book) => {
      try {
          const payload = {
              title: book.title,
              author: book.author,
              edition: book.edition
          };
          await api.post('/user/borrow-book', payload);
          alert(`Success! You have borrowed "${book.title}". Enjoy reading!`);
          
          const [allBooksRes, borrowedRes] = await Promise.all([
            api.get('/user/view-books'),
            api.get('/user/view-borrowed-books')
          ]);
          setBooks(allBooksRes.data.data || []);
          setBorrowedBooks(borrowedRes.data.data || []);
          
      } catch (err) {
          const errorMessage = err.response?.data?.message || 'Failed to borrow book.';
          alert(errorMessage);
      }
  };

  const handleReturn = async (book) => {
      try {
          const payload = {
              book: book
          };
          await api.post('/user/Return-book', payload);
          alert(`You have returned "${book.title}". Thank you!`);

          const [allBooksRes, borrowedRes] = await Promise.all([
            api.get('/user/view-books'),
            api.get('/user/view-borrowed-books')
          ]);
          setBooks(allBooksRes.data.data || []);
          setBorrowedBooks(borrowedRes.data.data || []);

      } catch (err) {
          const errorMessage = err.response?.data?.message || 'Failed to return book.';
          alert(errorMessage);
      }
  };

  return (
    <div className="min-h-screen bg-orange-50">
      <nav className="bg-white shadow-sm border-b border-orange-100">
        <div className="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8">
          <div className="flex justify-between h-20">
            <div className="flex items-center">
              <span className="text-3xl mr-3">📚</span>
              <div>
                <h1 className="text-xl font-bold text-amber-900 font-serif">My Local Library</h1>
                <p className="text-xs text-amber-600">Reading Room</p>
              </div>
            </div>
            
            <div className="flex items-center">
              <span className="mr-4 text-gray-500 text-sm hidden sm:block">Welcome, Reader!</span>
              <button
                onClick={handleLogout}
                className="bg-stone-200 hover:bg-stone-300 text-stone-700 px-4 py-2 rounded-lg text-sm font-medium transition-colors"
              >
                Leave Library (Logout)
              </button>
            </div>
          </div>
        </div>
      </nav>

      <main className="max-w-7xl mx-auto py-10 px-4 sm:px-6 lg:px-8">
        <div className="bg-amber-100 rounded-xl p-6 mb-8 border border-amber-200">
            <h2 className="text-2xl font-bold text-amber-900 font-serif mb-2">Welcome to the Reading Room!</h2>
            <p className="text-amber-800">
                Browse our collection below. If you see a book you like, you can borrow it!
            </p>
        </div>

        <div className="flex space-x-4 mb-6">
            <button
                onClick={() => setActiveTab('all-books')}
                className={`px-6 py-3 rounded-full font-bold transition-all duration-200 ${
                    activeTab === 'all-books' 
                    ? 'bg-amber-600 text-white shadow-md transform scale-105' 
                    : 'bg-white text-gray-500 hover:bg-white hover:text-amber-600'
                }`}
            >
                📖 Browse Books
            </button>
            
            <button
                onClick={() => setActiveTab('my-books')}
                className={`px-6 py-3 rounded-full font-bold transition-all duration-200 ${
                    activeTab === 'my-books' 
                    ? 'bg-amber-600 text-white shadow-md transform scale-105' 
                    : 'bg-white text-gray-500 hover:bg-white hover:text-amber-600'
                }`}
            >
                🎒 My Borrowed Books
            </button>
        </div>

        {loading ? (
            <div className="text-center py-20 text-gray-500">
                <span className="text-4xl block mb-2">⏳</span>
                Loading library data...
            </div>
        ) : (
            <div className="bg-white/50 backdrop-blur-sm rounded-xl">
            
            {activeTab === 'all-books' && (
                <div className="p-2">
                    {books.length === 0 ? (
                        <div className="text-center py-10 bg-white rounded-xl shadow-sm border border-stone-100">
                            <p className="text-gray-500">No books available in the library right now.</p>
                        </div>
                    ) : (
                        <div className="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 gap-6">
                            {books.map((book) => (
                                <div key={book.id || book.isbn} className="bg-white border border-stone-200 rounded-xl p-6 hover:shadow-lg transition-all duration-300 hover:-translate-y-1 group">
                                    <div className="flex justify-between items-start mb-4">
                                        <div className="h-12 w-12 bg-amber-100 rounded-lg flex items-center justify-center text-2xl group-hover:bg-amber-200 transition-colors">
                                            📕
                                        </div>
                                        <span className={`text-xs px-2 py-1 rounded-full font-bold ${
                                            book.status === 'AVAILABLE' ? 'bg-green-100 text-green-700' : 'bg-red-100 text-red-700'
                                        }`}>
                                            {book.status === 'AVAILABLE' ? 'Available' : 'Out of Stock'}
                                        </span>
                                    </div>
                                    
                                    <h3 className="text-xl font-bold text-gray-800 mb-1 font-serif line-clamp-1" title={book.title}>{book.title}</h3>
                                    <p className="text-sm text-gray-600 mb-4">by {book.author}</p>
                                    
                                    <button 
                                        onClick={() => handleBorrow(book)}
                                        disabled={book.status !== 'AVAILABLE'}
                                        className={`w-full py-2 rounded-lg font-bold text-sm transition-colors ${
                                            book.status === 'AVAILABLE' 
                                            ? 'bg-blue-600 text-white hover:bg-blue-700 shadow-sm' 
                                            : 'bg-gray-100 text-gray-400 cursor-not-allowed'
                                        }`}
                                    >
                                        {book.status === 'AVAILABLE' ? 'Borrow This Book' : 'Currently Unavailable'}
                                    </button>
                                </div>
                            ))}
                        </div>
                    )}
                </div>
            )}

            {activeTab === 'my-books' && (
                <div className="p-6 bg-white rounded-xl shadow-sm border border-stone-100">
                    <h2 className="text-xl font-bold text-gray-800 mb-6 font-serif">Your Backpack</h2>
                    
                    {borrowedBooks.length === 0 ? (
                        <div className="text-center py-10 text-gray-400">
                            <p className="text-4xl mb-4">🎒</p>
                            <p>Your backpack is empty!</p>
                            <p className="text-sm">Go to "Browse Books" to find something to read.</p>
                        </div>
                    ) : (
                        <ul className="divide-y divide-gray-100">
                            {borrowedBooks.map((book) => (
                                <li key={book.id} className="py-4">
                                    <div className="flex justify-between items-center">
                                        <div className="flex items-center">
                                            <span className="text-2xl mr-4">📖</span>
                                            <div>
                                                <p className="font-bold text-gray-900">{book.title || "Unknown Title"}</p>
                                                <p className="text-xs text-gray-500">by {book.author}</p>
                                            </div>
                                        </div>
                                        <button 
                                            onClick={() => handleReturn(book)}
                                            className="text-amber-600 hover:text-amber-800 text-sm font-bold border border-amber-200 px-3 py-1 rounded hover:bg-amber-50 transition"
                                        >
                                            Return Book
                                        </button>
                                    </div>
                                </li>
                            ))}
                        </ul>
                    )}
                </div>
            )}

          </div>
        )}
      </main>
    </div>
  );
};

export default Dashboard;
