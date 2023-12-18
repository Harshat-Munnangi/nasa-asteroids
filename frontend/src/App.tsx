import './App.css';
import { BrowserRouter, Route, Routes } from 'react-router-dom';
import AsteroidList from './components/AsteroidListPage';
import AsteroidDetailsPage from './components/AsteroidDetailsPage';

const App = () => (
	<BrowserRouter>
		<Routes>
      		<Route path={'/asteroids/:asteroidId'} element={<AsteroidDetailsPage />} />
      		<Route path={'/'} element={<AsteroidList />} />
		</Routes>
	</BrowserRouter>
);
export default App;
