
import React, { useState } from 'react';
import { useDispatch, useSelector } from 'react-redux';
import { useNavigate, Link } from 'react-router-dom';
import api from '../api/axiosConfig';
import { loginStart, loginSuccess, loginFailure } from '../redux/authSlice';


const Login = () => {
  const [formData, setFormData] = useState({
    email: '',
    password: '',
  });

  const [role, setRole] = useState('USER');
  const dispatch = useDispatch();
  const navigate = useNavigate();
  const { loading, error } = useSelector((state) => state.auth);

  const handleChange = (e) => {
    setFormData({
      ...formData, 
      [e.target.name]: e.target.value,
    });
  };

  const handleSubmit = async (e) => {
    e.preventDefault(); 
    dispatch(loginStart()); 

    const endpoint = role === 'ADMIN' ? '/auth/admin/login' : '/auth/user/login';

    try {
      const response = await api.post(endpoint, formData);
      const token = response.data.data.message;
      
      dispatch(loginSuccess({ token, role }));
      
      if (role === 'ADMIN') {
        navigate('/admin-dashboard'); 
      } else {
        navigate('/dashboard');
      }
    } catch (err) {
      const errorMessage = err.response?.data?.data?.message || err.response?.data?.message || 'Login failed. Please check your credentials.';
      dispatch(loginFailure(errorMessage));
    }
  };

  return (
  
    <div className="min-h-screen flex flex-col items-center justify-center bg-amber-50">
      
      <div className="text-center mb-8">
        <h1 className="text-4xl font-serif text-amber-900 mb-2"> My Local Library</h1>
        <p className="text-amber-700">Your gateway to a world of books</p>
      </div>

      <div className="max-w-md w-full bg-white p-8 rounded-xl shadow-lg border border-amber-100">
      
        <h2 className="text-2xl font-bold mb-2 text-center text-gray-800">
          Welcome Back!
        </h2>
  
        <p className="text-center text-gray-500 mb-6 text-sm">
          Please select your role and sign in to continue.
        </p>
        
        <div className="flex justify-center mb-8">
          <div className="bg-amber-100 rounded-full p-1 flex">
          
            <button
              type="button"
              onClick={() => setRole('USER')}
              className={`px-6 py-2 rounded-full text-sm font-medium transition-all duration-200 ${
                role === 'USER' 
                  ? 'bg-white text-amber-700 shadow-md transform scale-105' 
                  : 'text-amber-600 hover:text-amber-800' 
              }`}
            >
              Reader (User)
            </button>
           
            <button
              type="button"
              onClick={() => setRole('ADMIN')}
              className={`px-6 py-2 rounded-full text-sm font-medium transition-all duration-200 ${
                role === 'ADMIN' 
                  ? 'bg-white text-amber-700 shadow-md transform scale-105' 
                  : 'text-amber-600 hover:text-amber-800' 
              }`}
            >
              Librarian (Admin)
            </button>
          </div>
        </div>

        {error && (
          <div className="bg-red-50 border-l-4 border-red-500 text-red-700 p-4 mb-6 rounded">
            <p className="font-bold">Error</p>
            <p className="text-sm">{error}</p>
          </div>
        )}

        <form onSubmit={handleSubmit} className="space-y-4">
          
          <div>
            <label className="block text-gray-700 text-sm font-semibold mb-1" htmlFor="email">
              Email Address
            </label>
            <input
              type="email"
              name="email"
              value={formData.email}
              onChange={handleChange}
              placeholder="e.g. reader@example.com"
              className="w-full px-4 py-3 bg-gray-50 border border-gray-200 rounded-lg focus:outline-none focus:ring-2 focus:ring-amber-500 transition-colors"
              required
            />
          </div>

          <div>
            <label className="block text-gray-700 text-sm font-semibold mb-1" htmlFor="password">
              Password
            </label>
            <input
              type="password"
              name="password"
              value={formData.password}
              onChange={handleChange}
              placeholder="••••••••"
              className="w-full px-4 py-3 bg-gray-50 border border-gray-200 rounded-lg focus:outline-none focus:ring-2 focus:ring-amber-500 transition-colors"
              required
            />
          </div>

          <button
            type="submit"
            disabled={loading}
            className={`w-full py-3 px-4 rounded-lg text-white font-bold text-lg shadow-md transition-all
              ${loading 
                ? 'bg-amber-300 cursor-not-allowed'
                : 'bg-amber-600 hover:bg-amber-700 hover:shadow-lg transform hover:-translate-y-0.5'
              }`}
          >
            {loading ? 'Loading...' : 'Enter Library'}
          </button>
        </form>

        <div className="mt-6 text-center">
          <p className="text-gray-600 text-sm">
            New to our library?{' '}
            <Link to="/signup" className="text-amber-600 font-bold hover:underline">
              Get a Library Card (Sign Up)
            </Link>
          </p>
        </div>
      </div>
      
      <div className="mt-8 text-center text-gray-400 text-xs">
        <p>Library Management System • Beginner Edition</p>
      </div>
    </div>
  );
};

export default Login;
