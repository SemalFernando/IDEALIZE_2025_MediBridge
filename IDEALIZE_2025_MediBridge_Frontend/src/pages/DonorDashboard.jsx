import React, { useState, useEffect } from 'react';
import { useNavigate } from 'react-router-dom';
import Chatbot from '../components/Chatbot';
import '../styles/DonorDashboard.Module.css';

function DonorDashboard({ hospitals, onSelectHospital }) {
  const navigate = useNavigate();

  const [notifications, setNotifications] = useState([]);
const [showNotifications, setShowNotifications] = useState(false);

// Fetch notifications
const fetchNotifications = async () => {
  try {
    const res = await fetch('http://localhost:8082/api/campaigns/notifications');
    const data = await res.json();
setNotifications(data.map(notification => ({
  id: notification.id,
  message: notification.description, 
  time: formatTime(notification.createdAt), 
  read: false
})));
  } catch (error) {
    console.error('Failed to fetch notifications', error);
  }
};

// Time formatter
const formatTime = (timestamp) => {
  const now = new Date();
  const created = new Date(timestamp);
  const diffHours = Math.floor((now - created) / (1000 * 60 * 60));
  
  if (diffHours < 1) return 'Just now';
  if (diffHours < 24) return `${diffHours} hours ago`;
  return created.toLocaleDateString();
};

// Call in useEffect
useEffect(() => {
  fetchNotifications();
  const interval = setInterval(fetchNotifications, 60000); 
  return () => clearInterval(interval);
}, []);

  const handleSelectHospital = (hospital) => {
    window.scrollTo(0, 0);

    
    if (onSelectHospital) {
      onSelectHospital(hospital);
    }
    
    navigate('/hospital', { 
      state: { hospital },
      replace: true 
    });
  };

  return (
    <div className="donor-dashboard">
      <h1 className="dashboard-title">Welcome, Donor!</h1>

      <div className="dashboard-grid">
        <div className="dashboard-card">
          <h3>Total Donations</h3>
          <p className="amount">Rs. 25,000</p>
        </div>

        <div className="dashboard-card">
          <h3>Pending Donations</h3>
          <p className="amount">Rs. 3,000</p>
        </div>

        <div className="dashboard-card">
          <h3>Donation History</h3>
          <ul className="donation-history">
            <li>✅ Rs. 10,000 – St. Mary's Hospital</li>
            <li>✅ Rs. 12,000 – Children's Hospital</li>
            <li>🕓 Rs. 3,000 – City Care (Pending)</li>
          </ul>
        </div>

        <div className="dashboard-card full-width">
          <h3>Select a Hospital to Donate</h3>
          <p>Choose from our partner hospitals to make your donation</p>

          <div className="hospital-grid">
            {hospitals.map(hospital => (
              <div
                key={hospital.id}
                className="hospital-card"
                onClick={() => handleSelectHospital(hospital)}
              >
                <div
                  className="hospital-image"
                  style={{ backgroundImage: `url(${hospital.image})` }}
                ></div>
                <div className="hospital-info">
                  <h3>{hospital.name}</h3>
                  <div className="hospital-contact">
                    <span>📞 {hospital.contact}</span>
                    <span>✉️ {hospital.email}</span>
                  </div>
                  <button
                    className="select-hospital-btn"
                    onClick={(e) => {
                      e.stopPropagation();
                      handleSelectHospital(hospital);
                    }}
                  >
                    Select Hospital
                  </button>
                </div>
              </div>
            ))}
          </div>
        </div>
      </div>

      {/* Floating Chatbot */}
      <Chatbot />
    </div>
  );
}

export default DonorDashboard;