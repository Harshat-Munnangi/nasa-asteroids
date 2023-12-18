export interface Asteroid {
    id: string;
    name: string; 
    is_potentially_hazardous_asteroid: boolean;
    close_approach_data: CloseApproachData[];
}

export interface AsteroidDetails {
    id: string;
    neo_reference_id: string;
    name: string; 
    designation: string;
    nasa_jpl_url: string;
    absolute_magnitude_h: number;
    is_potentially_hazardous_asteroid: boolean;
}

interface CloseApproachData {
    close_approach_date: string;
    miss_distance: MissDistance;

}
interface MissDistance {
    kilometers: string;
}
