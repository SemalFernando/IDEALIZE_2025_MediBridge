import React, { useEffect, useState } from 'react';
import { useLocation } from 'react-router-dom';
import Chatbot from '../components/Chatbot';
import '../styles/HospitalPage.css';

const HospitalPage = ({ donationTypes = [], onBack, onDonate }) => {
  const location = useLocation();
  const [donorId, setDonorId] = useState(null);
  const [hospital, setHospital] = useState(null);

  useEffect(() => {
    window.scrollTo(0, 0);
    document.documentElement.scrollTop = 0;
    document.body.scrollTop = 0;
    
    // Get hospital from location state
    if (location.state?.hospital) {
      setHospital(location.state.hospital);
    }
    
    // Get donor ID from local storage (assuming it's stored after login)
    const donorData = localStorage.getItem('donorData');
    if (donorData) {
      const parsedData = JSON.parse(donorData);
      setDonorId(parsedData.userId);
    }
  }, [location]);

  const handleDonation = async (type) => {
    // Check if hospital exists
    if (!hospital) {
      console.error('Hospital is null');
      alert('Hospital information is not available. Please go back and select a hospital again.');
      return;
    }

    // Call the original onDonate function if provided
    if (onDonate) {
      onDonate(type.id);
    }

    try {
      console.log("Sending donation notification:", type.name);
      
      // Send notification to NGO
      const response = await fetch('http://localhost:8081/api/notifications', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
        },
body: JSON.stringify({
  message: `New donation of ${type.name} requested`,
  type: type.name.toLowerCase(),
  hospitalName: hospital?.name || "Unknown Hospital",
  donorId: donorId || 1
}),

      });

      console.log("Response status:", response.status);
      
      if (!response.ok) {
        const errorText = await response.text();
        console.error('Failed to send notification:', errorText);
        alert('Failed to send notification. Check console for details.');
      } else {
        const result = await response.json();
        console.log('Notification sent successfully:', result);
        alert('Donation request sent successfully!');
      }
    } catch (error) {
      console.error('Error sending notification:', error);
      alert('Error sending notification. Check console for details.');
    }
  };

  if (!hospital) {
    return (
      <div className="hospital-page container">
        <button className="back-button" onClick={onBack}>
          &larr; Back to Hospitals
        </button>
        
        <div className="no-hospital-selected">
          <p>No hospital selected. Please go back and select a hospital from the dashboard.</p>
        </div>
      </div>
    );
  }

  return (
    <div className="hospital-page container">
      <button className="back-button" onClick={onBack}>
        &larr; Back to Hospitals
      </button>

      <div className="hospital-card">
        <div className="hospital-header">
          <div className="hospital-text-content">
            <h1 className="hospital-name">{hospital.name}</h1>
          </div>
          <div
            className="hospital-image"
            style={{
              backgroundImage: `url(${hospital.image})`,
              minWidth: '220px'
            }}
            aria-label={`${hospital.name} facility`}
          />
        </div>

        <div className="hospital-details">
          <div className="detail-item">
            <span className="detail-icon">📍</span>
            <div>
              <h3>Address</h3>
              <p>{hospital.address}</p>
            </div>
          </div>

          <div className="detail-item">
            <span className="detail-icon">📞</span>
            <div>
              <h3>Contact</h3>
              <p>{hospital.contact}</p>
              <p>{hospital.email}</p>
            </div>
          </div>

          <div className="detail-item">
            <span className="detail-icon">⏰</span>
            <div>
              <h3>Operating Hours</h3>
              <p>{hospital.hours.split('\n').map((line, i) => (
                <React.Fragment key={i}>
                  {line}
                  <br />
                </React.Fragment>
              ))}</p>
            </div>
          </div>

          <div className="detail-item">
            <span className="detail-icon">🏥</span>
            <div>
              <h3>About</h3>
              <p>{hospital.description}</p>
            </div>
          </div>
        </div>
      </div>

      <div className="donation-types">
        <h2 className="section-title">Available Donation Types</h2>
        <div className="donation-grid">
          {donationTypes.map(type => (
            <div key={type.id} className="donation-card">
              <h3 className="donation-name">{type.name}</h3>
              <p className="donation-info">{type.info}</p>
<button
  className="donate-btn"
  onClick={() => handleDonation(type)}
  disabled={!hospital}  
>
  Donate {type.name.split(' ')[0]}
</button>

            </div>
          ))}
        </div>
      </div>

      {/* Floating Chatbot */}
      <Chatbot />
    </div>
  );
};

export default HospitalPage;