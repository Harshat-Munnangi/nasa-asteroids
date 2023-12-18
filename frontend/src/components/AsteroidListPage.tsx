import React, { useState, useEffect } from 'react';
import { Link } from 'react-router-dom';
import { getAsteroidsForWeek } from '../api/api';
import { Asteroid } from '../types/Asteroid';

const AsteroidList = () => {
  const [startDate, setStartDate] = useState('');
  const [endDate, setEndDate] = useState('');
  const [asteroids, setAsteroids] = useState<Asteroid[]>([]);
  const [loading, setLoading] = useState<boolean>(false);
  const [disableButton, setDisableButton] = useState<boolean>(true);
  const [errorMessage, setErrorMessage] = useState<string | null>(null);

  useEffect(() => {
    if(startDate && endDate) {
      setDisableButton(false)
    }
  }, [startDate, endDate]);

  const fetchData = () => {
    setLoading(true)
    setDisableButton(true)
    setAsteroids([])
    setErrorMessage(null)
    getAsteroidsForWeek(startDate, endDate)
    .then(data => {
      setAsteroids(data)
      setDisableButton(true)
    })
    .catch(error => {
      setErrorMessage(`Error fetching asteroids list: ${error.response.data.error_message}`)
      console.error('Error fetching asteroids:', error.response.data.error_message);
    })
    .finally(() => {
      setLoading(false)
    });
  }

  return (
    <div className="asteroid-list">
    <h2>Asteroid List</h2>
    
    <div className="form-container">
      <div className="form-row">
        <label>Start Date: </label>
        <input type="date" value={startDate} onChange={(e) => setStartDate(e.target.value)} />
      </div>

      <div className="form-row">
        <label>End Date: </label>
        <input type="date" value={endDate} onChange={(e) => setEndDate(e.target.value)} />
      </div>

      <button onClick={fetchData} disabled={loading || disableButton}>
        {loading ? 'Loading...' : 'Fetch Data'}
      </button>
    </div>
    {errorMessage != null && <p>{errorMessage}</p>}
    {asteroids.length > 0 &&
        <table className="asteroid-table">
        <thead>
          <tr>
            <th>Name</th>
            <th>Approach Date</th>
            <th>Distance</th>
            <th>Hazardous</th>
          </tr>
        </thead>
        <tbody>
          {asteroids.map((asteroid) => (
            <tr key={asteroid.id}>
              <td>
                <Link to={`/asteroids/${asteroid.id}`}>{asteroid.name}</Link>
              </td>
              <td>{asteroid.close_approach_data[0].close_approach_date}</td>
              <td>{asteroid.close_approach_data[0].miss_distance.kilometers || 'NA'}</td>
              <td>{asteroid.is_potentially_hazardous_asteroid ? 'Yes' : 'No'}</td>
            </tr>
          ))}
        </tbody>
      </table>
      }
    </div>
  );
};

export default AsteroidList;