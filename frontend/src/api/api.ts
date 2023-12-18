import axios from 'axios';
import { Asteroid, AsteroidDetails } from '../types/Asteroid';

const baseURL = 'http://localhost:8099/api/asteroids';


export const getAsteroidsForWeek = async (startDate: string, endDate: string): Promise<Asteroid[]> => {
  return (await axios.get<Asteroid[]>(`${baseURL}/week/${startDate}/${endDate}`)).data;
};

export const getAsteroidDetails = async (asteroidId: string): Promise<AsteroidDetails> => {
  return (await axios.get<AsteroidDetails>(`${baseURL}/${asteroidId}`)).data;
}