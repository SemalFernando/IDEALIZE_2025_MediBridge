// src/components/Header.js
import React, { useState, useEffect } from 'react';
import { Link } from 'react-router-dom';
import '../styles/Header.css';
import DonorProfile from './DonorProfile';

const Header = () => {
  const [showProfile, setShowProfile] = useState(false);
  const [showNotifications, setShowNotifications] = useState(false);
  const [notifications, setNotifications] = useState([]);

useEffect(() => {
  const fetchNotifications = async () => {
    try {
      const res = await fetch('http://localhost:8082/api/campaigns/notifications');
      const data = await res.json();
setNotifications(data.map(notification => ({
  id: notification.id,
  message: notification.description, // Use description as message
  time: formatTime(notification.createdAt), // Format time
  read: false
})));
    } catch (error) {
      console.error('Failed to fetch notifications', error);
    }
  };
  
  const formatTime = (isoString) => {
  const date = new Date(isoString);
  return date.toLocaleString();
};

  fetchNotifications();
  const interval = setInterval(fetchNotifications, 60000);
  return () => clearInterval(interval);
}, []);

  const toggleNotifications = () => {
    setShowNotifications(!showNotifications);
    // Mark all as read when opening
    if (!showNotifications) {
      setNotifications(notifications.map(n => ({ ...n, read: true })));
    }
  };

  return (
    <header className="app-header">
      <div className="header-content container">
        <div className="logo">
          <Link to="/" className="logo-text">
            <span className="logo-half medi">Medi</span>
            <span className="logo-half bridge">Bridge</span>
            <span className="corner-box"></span>
          </Link>
        </div>
        
        <div className="header-right">
          {/* Notification Button */}
          <div className="notification-wrapper">
            <button
              className="notification-btn"
              onClick={toggleNotifications}
            >
              <svg xmlns="http://www.w3.org/2000/svg" className="notification-icon" fill="none" viewBox="0 0 24 24" stroke="currentColor">
                <path strokeLinecap="round" strokeLinejoin="round" strokeWidth="2" d="M15 17h5l-1.405-1.405A2.032 2.032 0 0118 14.158V11a6.002 6.002 0 00-4-5.659V5a2 2 0 10-4 0v.341C7.67 6.165 6 8.388 6 11v3.159c0 .538-.214 1.055-.595 1.436L4 17h5m6 0v1a3 3 0 11-6 0v-1m6 0H9" />
              </svg>
              {notifications.some(n => !n.read) && (
                <span className="notification-badge"></span>
              )}
            </button>
            {showNotifications && (
              <div className="notification-dropdown">
                <div className="notification-header">
                  <h3>Notifications</h3>
                </div>
                <div className="notification-list">
                  {notifications.length > 0 ? (
                    notifications.map(notification => (
                      <div
                        key={notification.id}
                        className={`notification-item ${!notification.read ? 'unread' : ''}`}
                      >
                        <p>{notification.message}</p>
                        <span className="notification-time">{notification.time}</span>
                      </div>
                    ))
                  ) : (
                    <div className="no-notifications">
                      No new notifications
                    </div>
                  )}
                </div>
              </div>
            )}
          </div>
          
          {/* Profile Button */}
          <div className="profile-container">
            <div
              className="user-info"
              onClick={() => setShowProfile(!showProfile)}
            >
              <span className="profile-icon">
                <svg viewBox="0 0 24 24" width="24" height="24">
                  <path
                    fill="currentColor"
                    d="M12 12c2.21 0 4-1.79 4-4s-1.79-4-4-4-4 1.79-4 4 1.79 4 4 4zm0 2c-2.67 0-8 1.34-8 4v2h16v-2c0-2.66-5.33-4-8-4z"
                  />
                </svg>
              </span>
              <span className="user-name">Profile</span>
            </div>

            {showProfile && <DonorProfile />}
          </div>
        </div>
      </div>
    </header>
  );
};

export default Header;