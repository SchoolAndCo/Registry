public class Person {
    private Integer id;
    private String first_name;
    private String last_name;
    private String postal_code;
    private String city;
    private String street;
    private String house_number;
    private String country;

    public Person() {
        // Default constructor
    }

    public Person(Integer id, String first_name, String last_name, String postal_code, String city, String street, String house_number, String country) {
        this.id = id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.postal_code = postal_code;
        this.city = city;
        this.street = street;
        this.house_number = house_number;
        this.country = country;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return this.id;
    }

    public boolean update(String key, String value) {
        switch (key) {
            case "first-name":
                this.first_name = value;
                break;
            case "last-name":
                this.last_name = value;
                break;
            case "postal-code":
                this.postal_code = value;
                break;
            case "city":
                this.city = value;
                break;
            case "street":
                this.street = value;
                break;
            case "house-number":
                this.house_number = value;
                break;
            case "country":
                this.country = value;
                break;
            default:
                return false;
        }

        return true;
    }

    public String[] validate() {
        String[] errors = new String[30]; 
        int errorCount = 0;

        
        if (this.first_name == null || this.first_name.isEmpty()) {
            errors[errorCount++] = "First name is required.";
        } else {
            if (this.first_name.length() < 2) {
                errors[errorCount++] = "First name must be at least 2 characters long.";
            }
            if (!this.first_name.matches("[\\p{L} .'-]+")) {
                errors[errorCount++] = "First name contains invalid characters.";
            }
        }

        
        if (this.last_name == null || this.last_name.isEmpty()) {
            errors[errorCount++] = "Last name is required.";
        } else {
            if (this.last_name.length() < 2) {
                errors[errorCount++] = "Last name must be at least 2 characters long.";
            }
            if (!this.last_name.matches("[\\p{L} .'-]+")) {
                errors[errorCount++] = "Last name contains invalid characters.";
            }
        }

        
        if (this.postal_code == null || this.postal_code.isEmpty()) {
            errors[errorCount++] = "Postal code is required.";
        } else {
            if (!this.postal_code.matches("\\d{4,6}")) {
                errors[errorCount++] = "Postal code must be 4 to 6 digits.";
            }
        }

        
        if (this.city == null || this.city.isEmpty()) {
            errors[errorCount++] = "City is required.";
        } else {
            if (!this.city.matches("[\\p{L} .'-]+")) {
                errors[errorCount++] = "City name contains invalid characters.";
            }
            if (this.city.length() < 2) {
                errors[errorCount++] = "City name must be at least 2 characters long.";
            }
        }

        
        if (this.street == null || this.street.isEmpty()) {
            errors[errorCount++] = "Street is required.";
        } else {
            if (!this.street.matches("[\\p{L}\\d .,'/-]+")) {
                errors[errorCount++] = "Street contains invalid characters.";
            }
            if (this.street.length() < 2) {
                errors[errorCount++] = "Street must be at least 2 characters long.";
            }
        }

        
        if (this.house_number == null || this.house_number.isEmpty()) {
            errors[errorCount++] = "House number is required.";
        } else {
            if (!this.house_number.matches("[\\dA-Za-z/\\-]+")) {
                errors[errorCount++] = "House number format is invalid.";
            }
            if (this.house_number.length() > 10) {
                errors[errorCount++] = "House number is too long.";
            }
        }

        
        if (this.country == null || this.country.isEmpty()) {
            errors[errorCount++] = "Country is required.";
        } else {
            if (!this.country.matches("[\\p{L} ]{2,}")) {
                errors[errorCount++] = "Country must contain only letters and be at least 2 characters long.";
            }
            if (this.country.length() > 56) { 
                errors[errorCount++] = "Country name is too long.";
            }
        }

        return java.util.Arrays.copyOf(errors, errorCount);
    }

    public String getName() {
        return this.first_name + " " + this.last_name;
    } 

    public String getFullBody(Integer level) {
        String fullBody = "";

        fullBody += makeRow("First name", this.first_name, level);
        fullBody += makeRow("Last name", this.last_name, level);
        fullBody += makeRow("Postal code", this.postal_code, level);
        fullBody += makeRow("City", this.city, level);
        fullBody += makeRow("Street", this.street, level);
        fullBody += makeRow("House number", this.house_number, level);
        fullBody += makeRow("Country", this.country, level);

        return fullBody;
    }

    private String makeRow(String name, String value, Integer level) {
        return Helper.tabLevel(level)+name + ": " + (value == null ? "" : value) + "\n";
    }
}
