import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        FlightDatabase flightDB = new FlightDatabase();

        flightDB.checkIfFlightExists("Berlin", "Tokyo");
        System.out.println("=====");
        flightDB.displayFlightsFromCity("Paris");
        System.out.println("=====");
        flightDB.displayFlightToCity("Berlin");
        System.out.println("=====");
        ArrayList<String>cities = flightDB.getCities();
        System.out.println(cities);
        System.out.println("=====");
        Flight cheapestFlight = flightDB.getCheapestFlight();
        System.out.println("Cheapest flight is: "+ cheapestFlight.displayInfo());
        System.out.println("=====");
        ArrayList<Journey> journeys = flightDB.getFlights("Paris","Warsaw");
        System.out.println(journeys);
    }
}
    class Journey{
    Flight first;
    Flight second;

        @Override
        public String toString() {
            return "Flight from " + first.departure + " to " + second.arrival +  " with stop at "+first.arrival+ " costs "+ (first.price+second.price);
        }

        public Journey(Flight first, Flight second) {
            this.first = first;
            this.second = second;
        }
    }
    class Flight{
        String departure;
        String arrival;
        int price;

        public Flight(String departure, String arrival,int price) {
            this.departure = departure;
            this.arrival = arrival;
            this.price = price;
        }
        public String displayInfo(){
            return "Flight from " + this.departure + " to "+ this.arrival +" costs "+this.price;
        }
    }
    class FlightDatabase{
        ArrayList<Flight>flights = new ArrayList<>();

        public FlightDatabase(){
            this.flights.add(new Flight("Berlin","Tokyo",1800));
            this.flights.add(new Flight("New York","Berlin",1600));
            this.flights.add(new Flight("Madrid", "Berlin",700));
            this.flights.add(new Flight("Madrid", "Barcelona",200));
            this.flights.add(new Flight("Warsaw","Rome",560));
            this.flights.add(new Flight("Warsaw", "Madrid",999));
            this.flights.add(new Flight("Paris","London",690));
            this.flights.add(new Flight("London","Warsaw",850));
            this.flights.add(new Flight("Barcelona","New York",990));
            this.flights.add(new Flight("Rome","Barcelona",400));
            this.flights.add(new Flight("Berlin", "Paris",250));
            this.flights.add(new Flight("Paris", "Madrid",250));
            this.flights.add(new Flight("New York","Rome",1700));
        }
        public void checkIfFlightExists(String start,String end){
            for(Flight flight:this.flights){
                if (start.equals(flight.departure) && end.equals(flight.arrival)) {
                    System.out.println("Flight is in database");
                    return;
                }
                 System.out.println("Flight is missing from database");
            }
        }
        public ArrayList<Flight>getFlightFromCity(String city){
            ArrayList<Flight>results = new ArrayList<>();
            for (Flight flight : this.flights) {
                if (city.equals(flight.departure)) {
                    results.add(flight);
                }
            }
            return results;
        }
        public ArrayList<Flight>getFlightsToCity(String city){
            ArrayList<Flight>results=new ArrayList<>();
            for (Flight flight : this.flights) {
                if (city.equals(flight.arrival)) {
                    results.add(flight);
                }
            }
            return results;
        }
        public void displayFlights(ArrayList<Flight>results){
            if(results.isEmpty()){
                System.out.println("Flight not Found");
            }
            for (Flight flight : results) {
                System.out.println(flight.displayInfo());
            }
        }
        public void displayFlightsFromCity(String city){
            ArrayList<Flight>results = getFlightFromCity(city);
            displayFlights(results);
        }
        public void displayFlightToCity(String city){
            ArrayList<Flight>results=getFlightsToCity(city);
            displayFlights(results);
        }
        public ArrayList<String>getCities(){
            ArrayList<String>cities = new ArrayList<>();
            for(Flight flight :this.flights){
                if(!cities.contains(flight.departure)){
                    cities.add(flight.departure);
                }
                if(!cities.contains(flight.departure)){
                    cities.add(flight.arrival);
                }
            }
            return cities;
        }
        public Flight getCheapestFlight(){
            Flight cheapestFlight =null;
            for(Flight flight : this.flights){
                if(cheapestFlight == null || flight.price<cheapestFlight.price){
                    cheapestFlight=flight;
                }
            }
            return cheapestFlight;
        }
        public  Flight getCheapestFlightFromCity(String city){
            ArrayList<Flight>fromCity = getFlightFromCity(city);
            Flight cheapestFlight = null;
            for(Flight flight :fromCity){
                if(cheapestFlight == null || flight.price <cheapestFlight.price){
                    cheapestFlight = flight;
                }
            }
            return cheapestFlight;
        }
        public ArrayList<Journey> getFlights(String start,String end){
            ArrayList<Flight>starting = getFlightFromCity(start);
            ArrayList<Flight>ending = getFlightsToCity(end);
            ArrayList<Journey>result = new ArrayList<>();
            for(Flight first :starting){
                for(Flight second :ending){
                    if(first.arrival.equals(second.departure)){
                        result.add(new Journey(first,second));

                    }
                }
            }
            return result;
        }
}

