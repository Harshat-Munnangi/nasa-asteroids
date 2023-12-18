import React, { useState, useEffect } from 'react';
import { Link, useParams } from 'react-router-dom';
import { getAsteroidDetails } from '../api/api';
import { AsteroidDetails } from '../types/Asteroid';
import Loader from './Loader';

const AsteroidDetailsPage = () => {
  const { asteroidId } = useParams();
  const [asteroid, setAsteroid] = useState<AsteroidDetails | null>(null);
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    setLoading(true)
    getAsteroidDetails(asteroidId!!)
    .then(data => {
      setAsteroid(data)
    })
    .catch(error => {
      console.error('Error fetching asteroid details:', error.message);
    })
    .finally(() => setLoading(false))
  }, [asteroidId]);

  return (
    <div className="asteroid-details">
  <h2>Asteroid Details</h2>
  {loading ? (
    <Loader />
  ) : (
    <div className="asteroid-details-info">
      <table className="asteroid-details-table">
        <tbody>
          <tr>
            <td>Name:</td>
            <td>{asteroid?.name}</td>
          </tr>
          <tr>
            <td>Reference Id:</td>
            <td>{asteroid?.neo_reference_id}</td>
          </tr>
          <tr>
            <td>Designation:</td>
            <td>{asteroid?.designation}</td>
          </tr>
          <tr>
            <td>Look Up:</td>
            <td>
              <a href={asteroid?.nasa_jpl_url} target="_blank" rel="noopener noreferrer">{asteroid?.nasa_jpl_url}</a>
            </td>
          </tr>
          <tr>
            <td>Magnitude:</td>
            <td>{asteroid?.absolute_magnitude_h}</td>
          </tr>
          <tr>
            <td>Potentially Hazardous:</td>
            <td>{asteroid?.is_potentially_hazardous_asteroid ? 'Yes' : 'No'}</td>
          </tr>
        </tbody>
      </table>
      <Link to="/">
        <button>Back to Asteroids List</button>
      </Link>
    </div>
  )}
</div>

  );
};

export default AsteroidDetailsPage;