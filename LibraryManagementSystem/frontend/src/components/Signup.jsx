import React, { useState } from 'react';
import { useNavigate, Link } from 'react-router-dom';
import api from '../api/axiosConfig';

const Signup = () => {
  const navigate = useNavigate();
  
  const [formData, setFormData] = useState({
    fullName: '',   
    email: '',      
    phone: '',      
    userName: '', 
    password: '',  
  });

  const [loading, setLoading] = useState(false);
  const [error, setError] = useState(null);

  
  const handleChange = (e) => {
    setFormData({
      ...formData, 
      [e.target.name]: e.target.value, 
    });
  };

  
  const handleSubmit = async (e) => {
    e.preventDefault(); 
    setLoading(true); 
    setError(null); 

    const endpoint = '/auth/user/signup';

    try {
  
      await api.post(endpoint, formData);
      alert('Registration successful! Welcome to the club. Please login.');
      navigate('/login');
    } catch (err) {
      const errorMessage = err.response?.data?.message || 'Registration failed. Please try again.';
      setError(errorMessage);
    } finally {
      setLoading(false);
    }
  };

  return (
    
    <div className="min-h-screen flex flex-col items-center justify-center bg-amber-50 py-10">
      
      <div className="text-center mb-8">
        <h1 className="text-3xl font-serif text-amber-900 mb-2"> Join Our Library</h1>
        <p className="text-amber-700">Start your reading journey today</p>
      </div>

      <div className="max-w-md w-full bg-white p-8 rounded-xl shadow-lg border border-amber-100">
        <h2 className="text-xl font-bold mb-6 text-center text-gray-800">
          Member Registration
        </h2>

        {error && (
          <div className="bg-red-50 border-l-4 border-red-500 text-red-700 p-4 mb-6 rounded">
            <p className="font-bold">Oops!</p>
            <p className="text-sm">{error}</p>
          </div>
        )}

        <form onSubmit={handleSubmit} className="space-y-4">

          <div>
            <label className="block text-gray-700 text-sm font-semibold mb-1" htmlFor="fullName">
              Full Name
            </label>
            <input
              type="text"
              name="fullName"
              value={formData.fullName}
              onChange={handleChange}
              placeholder="John Doe"
              className="w-full px-4 py-2 bg-gray-50 border border-gray-200 rounded-lg focus:outline-none focus:ring-2 focus:ring-amber-500 transition-colors"
              required
            />
          </div>

          <div>
            <label className="block text-gray-700 text-sm font-semibold mb-1" htmlFor="email">
              Email Address
            </label>
            <input
              type="email"
              name="email"
              value={formData.email}
              onChange={handleChange}
              placeholder="john@example.com"
              className="w-full px-4 py-2 bg-gray-50 border border-gray-200 rounded-lg focus:outline-none focus:ring-2 focus:ring-amber-500 transition-colors"
              required
            />
          </div>

          <div>
            <label className="block text-gray-700 text-sm font-semibold mb-1" htmlFor="phone">
              Phone Number
            </label>
            <input
              type="text"
              name="phone"
              value={formData.phone}
              onChange={handleChange}
              placeholder="(555) 123-4567"
              className="w-full px-4 py-2 bg-gray-50 border border-gray-200 rounded-lg focus:outline-none focus:ring-2 focus:ring-amber-500 transition-colors"
              required
            />
          </div>

          <div>
            <label className="block text-gray-700 text-sm font-semibold mb-1" htmlFor="userName">
              Choose a Username
            </label>
            <input
              type="text"
              name="userName"
              value={formData.userName}
              onChange={handleChange}
              placeholder="bookworm123"
              className="w-full px-4 py-2 bg-gray-50 border border-gray-200 rounded-lg focus:outline-none focus:ring-2 focus:ring-amber-500 transition-colors"
              required
            />
          </div>

          <div>
            <label className="block text-gray-700 text-sm font-semibold mb-1" htmlFor="password">
              Create Password
            </label>
            <input
              type="password"
              name="password"
              value={formData.password}
              onChange={handleChange}
              placeholder="••••••••"
              className="w-full px-4 py-2 bg-gray-50 border border-gray-200 rounded-lg focus:outline-none focus:ring-2 focus:ring-amber-500 transition-colors"
              required
            />
          </div>

          <button
            type="submit"
            disabled={loading}
            className={`w-full py-3 px-4 mt-4 rounded-lg text-white font-bold text-lg shadow-md transition-all
              ${loading 
                ? 'bg-amber-300 cursor-not-allowed'
                : 'bg-amber-600 hover:bg-amber-700 hover:shadow-lg transform hover:-translate-y-0.5'
              }`}
          >
            {loading ? 'Creating Card...' : 'Sign Up'}
          </button>
        </form>

        <div className="mt-6 text-center">
          <p className="text-gray-600 text-sm">
            Already have a card?{' '}
            <Link to="/login" className="text-amber-600 font-bold hover:underline">
              Log In Here
            </Link>
          </p>
        </div>
      </div>
    </div>
  );
};

export default Signup;
