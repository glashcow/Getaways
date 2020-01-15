package com.fdmgroup.getaways.model.flights;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import com.fdmgroup.getaways.model.AccountUser;
import org.springframework.format.annotation.DateTimeFormat;
import com.fdmgroup.getaways.model.airports.Airports;

@Entity(name="FLIGHTS")
public class Flight implements Trip{

	@Id
	@Column(name="FLIGHT_ID")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="flight_gen")
	@SequenceGenerator(name="flight_gen", sequenceName = "flight_seq", allocationSize = 1)
	private Long flightId;
	@Column(name="FLIGHT_NAME")
	private String flightName;
	@Column(name="AIRPORT_START")
	@Enumerated(EnumType.STRING)
	private Airports startLocation;
	@Column(name="AIRPORT_END")
	@Enumerated(EnumType.STRING)
	private Airports endLocation;
	@Column(name="FLIGHT_DURATION")
	private Duration flightDuration;
	@Temporal(TemporalType.DATE)
	@Column(name="DEPARTURE_DATE")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date dateOfDeparture;
	@Column(name="DEPARTURE_TIME")
	@DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
	private Date departureTime;
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="ARRIVAL_TIME")
	@DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
	private Date arrivalTime;
	@Column(name="PRICE")
	private float flightPrice;
	@Column (name ="FLIGHT_CAPACITY")
	private int flightCapacity;
	@OneToOne
	@JoinTable(name="AccountUser")
	private AccountUser airline;

	public Flight() {
		
	}
	
	public Flight(String flightName, Airports startLocation, Airports endLocation,
			Duration flightDuration, Date departureTime, Date arrivalTime, float flightPrice,  int flightCapacity) {
		this.flightName = flightName;
		this.startLocation = startLocation;
		this.endLocation = endLocation;
		this.flightDuration = flightDuration;
		this.departureTime = departureTime;
		this.arrivalTime = arrivalTime;
		this.flightPrice = flightPrice;
		this.flightCapacity = flightCapacity;
	}

	public Long getFlightId() {
		return flightId;
	}

	public void setFlightId(Long flightId) {
		this.flightId = flightId;
	}


	public String getFlightName() {
		return flightName;
	}

	public void setFlightName(String flightName) {
		this.flightName = flightName;
	}

	public Airports getStartLocation() {
		return startLocation;
	}

	public void setStartLocation(Airports startLocation) {
		this.startLocation = startLocation;
	}

	public Airports getEndLocation() {
		return endLocation;
	}

	public void setEndLocation(Airports endLocation) {
		this.endLocation = endLocation; 
	}

	public Duration getFlightDuration() {
		return flightDuration;
	}

	public void setFlightDuration(Date arrivalTime, Date departureTime) {
		LocalDateTime ldtArrival  = LocalDateTime.ofInstant(arrivalTime.toInstant(),
                ZoneId.systemDefault());
		LocalDateTime ldtDeparture  = LocalDateTime.ofInstant(departureTime.toInstant(),
                ZoneId.systemDefault()); 
		this.flightDuration = Duration.between(ldtDeparture, ldtArrival);
	}

	public Date getDepartureTime() {
		return departureTime;
	}

	public void setDepartureTime(Date departureTime) {
		this.departureTime = departureTime;
	}

	public Date getArrivalTime() {
		return arrivalTime;
	}

	public void setArrivalTime(Date arrivalTime) {
		this.arrivalTime = arrivalTime;
	}

	public float getFlightPrice() {
		return flightPrice;
	}

	public void setFlightPrice(float flightPrice) {
		this.flightPrice = flightPrice;
	}
	
	public int getFlightCapacity() {
		return flightCapacity;
	}

	public void setFlightCapacity(int flightCapacity) {
		this.flightCapacity = flightCapacity;
	}

	public AccountUser getAirline() {
		return airline;
	}

	public void setAirline(AccountUser airline) {
		this.airline = airline;
	}

	
	public Date getDateOfDeparture() {
		return dateOfDeparture;
	}

	public void setDateOfDeparture(Date dateOfDeparture) {
		this.dateOfDeparture = dateOfDeparture;
	}

	public void setFlightDuration(Duration flightDuration) {
		this.flightDuration = flightDuration;
	}
	
	@Override
	public String toString() {
		return "Flight [flightId=" + flightId + ", flightName=" + flightName + ", startLocation=" + startLocation
				+ ", endLocation=" + endLocation + ", flightDuration=" + flightDuration + ", dateOfDeparture="
				+ dateOfDeparture + ", departureTime=" + departureTime + ", arrivalTime=" + arrivalTime + ", price="
				+ flightPrice + "]";
	}
	
}
