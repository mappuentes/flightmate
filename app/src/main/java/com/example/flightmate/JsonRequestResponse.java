package com.example.flightmate;

import com.google.gson.annotations.SerializedName;
import java.util.List;
import java.util.Map;

public class JsonRequestResponse {

  private Request request;
  private List<Flight> response;
  private String terms;

  // Getters and setters...
  public Request getRequest() {
    return request;
  }

  public void setRequest(Request request) {
    this.request = request;
  }

  public List<Flight> getResponse() {
    return response;
  }

  public void setResponse(List<Flight> response) {
    this.response = response;
  }

  public String getTerms() {
    return terms;
  }

  public void setTerms(String terms) {
    this.terms = terms;
  }

  // --------------------------------------

  public static class Request {

    private String lang;
    private String currency;
    private int time;
    private String id;
    private String server;
    private String host;
    private int pid;
    private Key key;
    private Map<String, String> params;
    private int version;
    private String method;
    private Client client;

    // Getters and setters...
    public String getLang() {
      return lang;
    }

    public void setLang(String lang) {
      this.lang = lang;
    }

    public String getCurrency() {
      return currency;
    }

    public void setCurrency(String currency) {
      this.currency = currency;
    }

    public int getTime() {
      return time;
    }

    public void setTime(int time) {
      this.time = time;
    }

    public String getId() {
      return id;
    }

    public void setId(String id) {
      this.id = id;
    }

    public String getServer() {
      return server;
    }

    public void setServer(String server) {
      this.server = server;
    }

    public String getHost() {
      return host;
    }

    public void setHost(String host) {
      this.host = host;
    }

    public int getPid() {
      return pid;
    }

    public void setPid(int pid) {
      this.pid = pid;
    }

    public Key getKey() {
      return key;
    }

    public void setKey(Key key) {
      this.key = key;
    }

    public Map<String, String> getParams() {
      return params;
    }

    public void setParams(Map<String, String> params) {
      this.params = params;
    }

    public int getVersion() {
      return version;
    }

    public void setVersion(int version) {
      this.version = version;
    }

    public String getMethod() {
      return method;
    }

    public void setMethod(String method) {
      this.method = method;
    }

    public Client getClient() {
      return client;
    }

    public void setClient(Client client) {
      this.client = client;
    }

    // --------------------------------------

    public static class Key {

      private int id;

      @SerializedName("api_key")
      private String apiKey;

      private String type;
      private String expired;
      private String registered;

      @SerializedName("limits_by_hour")
      private int limitsByHour;

      @SerializedName("limits_by_minute")
      private int limitsByMinute;

      @SerializedName("limits_by_month")
      private int limitsByMonth;

      @SerializedName("limits_total")
      private int limitsTotal;

      // Getters and setters...
      public int getId() {
        return id;
      }

      public void setId(int id) {
        this.id = id;
      }

      public String getApiKey() {
        return apiKey;
      }

      public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
      }

      public String getType() {
        return type;
      }

      public void setType(String type) {
        this.type = type;
      }

      public String getExpired() {
        return expired;
      }

      public void setExpired(String expired) {
        this.expired = expired;
      }

      public String getRegistered() {
        return registered;
      }

      public void setRegistered(String registered) {
        this.registered = registered;
      }

      public int getLimitsByHour() {
        return limitsByHour;
      }

      public void setLimitsByHour(int limitsByHour) {
        this.limitsByHour = limitsByHour;
      }

      public int getLimitsByMinute() {
        return limitsByMinute;
      }

      public void setLimitsByMinute(int limitsByMinute) {
        this.limitsByMinute = limitsByMinute;
      }

      public int getLimitsByMonth() {
        return limitsByMonth;
      }

      public void setLimitsByMonth(int limitsByMonth) {
        this.limitsByMonth = limitsByMonth;
      }

      public int getLimitsTotal() {
        return limitsTotal;
      }

      public void setLimitsTotal(int limitsTotal) {
        this.limitsTotal = limitsTotal;
      }
    }

    // --------------------------------------

    public static class Client {

      private String ip;
      private Geo geo;
      private Connection connection;
      private Map<String, Object> device;
      private Map<String, Object> agent;
      private Karma karma;

      // Getters and setters...
      public String getIp() {
        return ip;
      }

      public void setIp(String ip) {
        this.ip = ip;
      }

      public Geo getGeo() {
        return geo;
      }

      public void setGeo(Geo geo) {
        this.geo = geo;
      }

      public Connection getConnection() {
        return connection;
      }

      public void setConnection(Connection connection) {
        this.connection = connection;
      }

      public Map<String, Object> getDevice() {
        return device;
      }

      public void setDevice(Map<String, Object> device) {
        this.device = device;
      }

      public Map<String, Object> getAgent() {
        return agent;
      }

      public void setAgent(Map<String, Object> agent) {
        this.agent = agent;
      }

      public Karma getKarma() {
        return karma;
      }

      public void setKarma(Karma karma) {
        this.karma = karma;
      }

      // --------------------------------------

      public static class Geo {

        @SerializedName("country_code")
        private String countryCode;

        private String country;
        private String continent;
        private String city;
        private double lat;
        private double lng;
        private String timezone;

        // Getters and setters...
        public String getCountryCode() {
          return countryCode;
        }

        public void setCountryCode(String countryCode) {
          this.countryCode = countryCode;
        }

        public String getCountry() {
          return country;
        }

        public void setCountry(String country) {
          this.country = country;
        }

        public String getContinent() {
          return continent;
        }

        public void setContinent(String continent) {
          this.continent = continent;
        }

        public String getCity() {
          return city;
        }

        public void setCity(String city) {
          this.city = city;
        }

        public double getLat() {
          return lat;
        }

        public void setLat(double lat) {
          this.lat = lat;
        }

        public double getLng() {
          return lng;
        }

        public void setLng(double lng) {
          this.lng = lng;
        }

        public String getTimezone() {
          return timezone;
        }

        public void setTimezone(String timezone) {
          this.timezone = timezone;
        }
      }

      // --------------------------------------

      public static class Connection {

        private String type;

        @SerializedName("isp_code")
        private int ispCode;

        @SerializedName("isp_name")
        private String ispName;

        // Getters and setters...
        public String getType() {
          return type;
        }

        public void setType(String type) {
          this.type = type;
        }

        public int getIspCode() {
          return ispCode;
        }

        public void setIspCode(int ispCode) {
          this.ispCode = ispCode;
        }

        public String getIspName() {
          return ispName;
        }

        public void setIspName(String ispName) {
          this.ispName = ispName;
        }
      }

      // --------------------------------------

      public static class Karma {

        @SerializedName("is_blocked")
        private boolean isBlocked;

        @SerializedName("is_crawler")
        private boolean isCrawler;

        @SerializedName("is_bot")
        private boolean isBot;

        @SerializedName("is_friend")
        private boolean isFriend;

        @SerializedName("is_regular")
        private boolean isRegular;

        // Getters and setters...
        public boolean isBlocked() {
          return isBlocked;
        }

        public void setBlocked(boolean isBlocked) {
          this.isBlocked = isBlocked;
        }

        public boolean isCrawler() {
          return isCrawler;
        }

        public void setCrawler(boolean isCrawler) {
          this.isCrawler = isCrawler;
        }

        public boolean isBot() {
          return isBot;
        }

        public void setBot(boolean isBot) {
          this.isBot = isBot;
        }

        public boolean isFriend() {
          return isFriend;
        }

        public void setFriend(boolean isFriend) {
          this.isFriend = isFriend;
        }

        public boolean isRegular() {
          return isRegular;
        }

        public void setRegular(boolean isRegular) {
          this.isRegular = isRegular;
        }
      }
    }
  }

  // --------------------------------------

  public static class Flight {

    @SerializedName("airline_iata")
    private String airlineIata;

    @SerializedName("airline_icao")
    private String airlineIcao;

    @SerializedName("flight_iata")
    private String flightIata;

    @SerializedName("flight_icao")
    private String flightIcao;

    @SerializedName("flight_number")
    private String flightNumber;

    @SerializedName("dep_iata")
    private String depIata;

    @SerializedName("dep_icao")
    private String depIcao;

    @SerializedName("dep_terminal")
    private String depTerminal;

    @SerializedName("dep_gate")
    private String depGate;

    @SerializedName("dep_time")
    private String depTime;

    @SerializedName("dep_time_utc")
    private String depTimeUtc;

    @SerializedName("dep_estimated")
    private String depEstimated;

    @SerializedName("dep_estimated_utc")
    private String depEstimatedUtc;

    @SerializedName("dep_actual")
    private String depActual;

    @SerializedName("dep_actual_utc")
    private String depActualUtc;

    @SerializedName("arr_iata")
    private String arrIata;

    @SerializedName("arr_icao")
    private String arrIcao;

    @SerializedName("arr_terminal")
    private String arrTerminal;

    @SerializedName("arr_gate")
    private String arrGate;

    @SerializedName("arr_baggage")
    private String arrBaggage;

    @SerializedName("arr_time")
    private String arrTime;

    @SerializedName("arr_time_utc")
    private String arrTimeUtc;

    @SerializedName("arr_estimated")
    private String arrEstimated;

    @SerializedName("arr_estimated_utc")
    private String arrEstimatedUtc;

    @SerializedName("cs_airline_iata")
    private String csAirlineIata;

    @SerializedName("cs_flight_number")
    private String csFlightNumber;

    @SerializedName("cs_flight_iata")
    private String csFlightIata;

    private String status;
    private int duration;
    private Integer delayed;

    @SerializedName("dep_delayed")
    private Integer depDelayed;

    @SerializedName("arr_delayed")
    private Integer arrDelayed;

    @SerializedName("aircraft_icao")
    private String aircraftIcao;

    @SerializedName("arr_time_ts")
    private long arrTimeTs;

    @SerializedName("dep_time_ts")
    private long depTimeTs;

    @SerializedName("arr_estimated_ts")
    private long arrEstimatedTs;

    @SerializedName("dep_estimated_ts")
    private long depEstimatedTs;

    @SerializedName("dep_actual_ts")
    private long depActualTs;

    // Getters and setters...
    public String getAirlineIata() {
      return airlineIata;
    }

    public void setAirlineIata(String airlineIata) {
      this.airlineIata = airlineIata;
    }

    public String getAirlineIcao() {
      return airlineIcao;
    }

    public void setAirlineIcao(String airlineIcao) {
      this.airlineIcao = airlineIcao;
    }

    public String getFlightIata() {
      return flightIata;
    }

    public void setFlightIata(String flightIata) {
      this.flightIata = flightIata;
    }

    public String getFlightIcao() {
      return flightIcao;
    }

    public void setFlightIcao(String flightIcao) {
      this.flightIcao = flightIcao;
    }

    public String getFlightNumber() {
      return flightNumber;
    }

    public void setFlightNumber(String flightNumber) {
      this.flightNumber = flightNumber;
    }

    public String getDepIata() {
      return depIata;
    }

    public void setDepIata(String depIata) {
      this.depIata = depIata;
    }

    public String getDepIcao() {
      return depIcao;
    }

    public void setDepIcao(String depIcao) {
      this.depIcao = depIcao;
    }

    public String getDepTerminal() {
      return depTerminal;
    }

    public void setDepTerminal(String depTerminal) {
      this.depTerminal = depTerminal;
    }

    public String getDepGate() {
      return depGate;
    }

    public void setDepGate(String depGate) {
      this.depGate = depGate;
    }

    public String getDepTime() {
      return depTime;
    }

    public void setDepTime(String depTime) {
      this.depTime = depTime;
    }

    public String getDepTimeUtc() {
      return depTimeUtc;
    }

    public void setDepTimeUtc(String depTimeUtc) {
      this.depTimeUtc = depTimeUtc;
    }

    public String getDepEstimated() {
      return depEstimated;
    }

    public void setDepEstimated(String depEstimated) {
      this.depEstimated = depEstimated;
    }

    public String getDepEstimatedUtc() {
      return depEstimatedUtc;
    }

    public void setDepEstimatedUtc(String depEstimatedUtc) {
      this.depEstimatedUtc = depEstimatedUtc;
    }

    public String getDepActual() {
      return depActual;
    }

    public void setDepActual(String depActual) {
      this.depActual = depActual;
    }

    public String getDepActualUtc() {
      return depActualUtc;
    }

    public void setDepActualUtc(String depActualUtc) {
      this.depActualUtc = depActualUtc;
    }

    public String getArrIata() {
      return arrIata;
    }

    public void setArrIata(String arrIata) {
      this.arrIata = arrIata;
    }

    public String getArrIcao() {
      return arrIcao;
    }

    public void setArrIcao(String arrIcao) {
      this.arrIcao = arrIcao;
    }

    public String getArrTerminal() {
      return arrTerminal;
    }

    public void setArrTerminal(String arrTerminal) {
      this.arrTerminal = arrTerminal;
    }

    public String getArrGate() {
      return arrGate;
    }

    public void setArrGate(String arrGate) {
      this.arrGate = arrGate;
    }

    public String getArrBaggage() {
      return arrBaggage;
    }

    public void setArrBaggage(String arrBaggage) {
      this.arrBaggage = arrBaggage;
    }

    public String getArrTime() {
      return arrTime;
    }

    public void setArrTime(String arrTime) {
      this.arrTime = arrTime;
    }

    public String getArrTimeUtc() {
      return arrTimeUtc;
    }

    public void setArrTimeUtc(String arrTimeUtc) {
      this.arrTimeUtc = arrTimeUtc;
    }

    public String getArrEstimated() {
      return arrEstimated;
    }

    public void setArrEstimated(String arrEstimated) {
      this.arrEstimated = arrEstimated;
    }

    public String getArrEstimatedUtc() {
      return arrEstimatedUtc;
    }

    public void setArrEstimatedUtc(String arrEstimatedUtc) {
      this.arrEstimatedUtc = arrEstimatedUtc;
    }

    public String getCsAirlineIata() {
      return csAirlineIata;
    }

    public void setCsAirlineIata(String csAirlineIata) {
      this.csAirlineIata = csAirlineIata;
    }

    public String getCsFlightNumber() {
      return csFlightNumber;
    }

    public void setCsFlightNumber(String csFlightNumber) {
      this.csFlightNumber = csFlightNumber;
    }

    public String getCsFlightIata() {
      return csFlightIata;
    }

    public void setCsFlightIata(String csFlightIata) {
      this.csFlightIata = csFlightIata;
    }

    public String getStatus() {
      return status;
    }

    public void setStatus(String status) {
      this.status = status;
    }

    public int getDuration() {
      return duration;
    }

    public void setDuration(int duration) {
      this.duration = duration;
    }

    public Integer getDelayed() {
      return delayed;
    }

    public void setDelayed(Integer delayed) {
      this.delayed = delayed;
    }

    public Integer getDepDelayed() {
      return depDelayed;
    }

    public void setDepDelayed(Integer depDelayed) {
      this.depDelayed = depDelayed;
    }

    public Integer getArrDelayed() {
      return arrDelayed;
    }

    public void setArrDelayed(Integer arrDelayed) {
      this.arrDelayed = arrDelayed;
    }

    public String getAircraftIcao() {
      return aircraftIcao;
    }

    public void setAircraftIcao(String aircraftIcao) {
      this.aircraftIcao = aircraftIcao;
    }

    public long getArrTimeTs() {
      return arrTimeTs;
    }

    public void setArrTimeTs(long arrTimeTs) {
      this.arrTimeTs = arrTimeTs;
    }

    public long getDepTimeTs() {
      return depTimeTs;
    }

    public void setDepTimeTs(long depTimeTs) {
      this.depTimeTs = depTimeTs;
    }

    public long getArrEstimatedTs() {
      return arrEstimatedTs;
    }

    public void setArrEstimatedTs(long arrEstimatedTs) {
      this.arrEstimatedTs = arrEstimatedTs;
    }

    public long getDepEstimatedTs() {
      return depEstimatedTs;
    }

    public void setDepEstimatedTs(long depEstimatedTs) {
      this.depEstimatedTs = depEstimatedTs;
    }

    public long getDepActualTs() {
      return depActualTs;
    }

    public void setDepActualTs(long depActualTs) {
      this.depActualTs = depActualTs;
    }
  }
}
