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
        //flightDB.getCities();
    }
}
    class Flight{
        String departure;
        String arrival;

        public Flight(String departure, String arrival) {
            this.departure = departure;
            this.arrival = arrival;
        }
        public String displayInfo(){
            return "Flight from " + this.departure + " to "+ this.arrival;
        }
    }
    class FlightDatabase{
        ArrayList<Flight>flights = new ArrayList<>();

        public FlightDatabase(){
            this.flights.add(new Flight("Berlin","Tokyo"));
            this.flights.add(new Flight("New York","Berlin"));
            this.flights.add(new Flight("Madrid", "Berlin"));
            this.flights.add(new Flight("Madrid", "Barcelona"));
            this.flights.add(new Flight("Warsaw","Rome"));
            this.flights.add(new Flight("Warsaw", "Madrid"));
            this.flights.add(new Flight("Paris","London"));
            this.flights.add(new Flight("London","Warsaw"));
            this.flights.add(new Flight("Barcelona","New York"));
            this.flights.add(new Flight("Rome","Barcelona"));
            this.flights.add(new Flight("Berlin", "Paris"));
            this.flights.add(new Flight("Paris", "Madrid"));
            this.flights.add(new Flight("New York","Rome"));
        }
        public void checkIfFlightExists(String start,String end){
            for(Flight flight:this.flights){
                if (start.equals(flight.departure) && end.equals(flight.arrival)) {
                    System.out.println(flight.displayInfo() + " is in database");
                    return;
                }
                 System.out.println(flight.displayInfo()+" is missing from database");
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
//        public ArrayList<Flight>getCities(ArrayList<Flight>results){
//            ArrayList<Flight>result = new ArrayList<>();
//            for(Flight flight :this.flights){
//                if(re.contains(results)){
//                    result.add(flight);
//                }
//            }
//            return result;
//        }

}

