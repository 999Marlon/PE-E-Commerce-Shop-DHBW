# 1. Domain Driven Design

## 1.1 Analyse der Ubiquitous Language

Die Ubiquitous Language meines Projektes beläuft sich auf folgende beispielhafte Begriffe:

- **Produkt**: Ein Artikel, der im Shop angeboten wird. Hat eine UUID, Namen, Bild, Beschreibung, Preis und Kategorie.
- **Address**: Ein Wertobjekt, das die Adresse des Benutzers enthält (Straße, Stadt, PLZ und Land).
- **Benutzer**: Eine registrierte Person, die sich im Shop anmeldet und Einkäufe tätigt. Dieser wird beschrieben durch eine UUID, einen Benutzernamen, eine Adresse, eine E-Mail und ein Passwort.
- **Warenkorb**: Temporärer Speicher für vom Benutzer ausgewählte Produkte. Enthält eine UUID, einen zugehörigen Benutzer und eine Liste an Produkten.
- **Bestellungsstatus**: Der Status der Bestellung.
- **Bestellung**: Wird aus dem Warenkorb generiert, sobald der Benutzer zur Kasse geht. Enthält eine UUID, den zugehörigen Benutzer, eine Liste an Produkten, einen Bestellungsstatus und eine Adresse, an die geschickt werden soll.
- **Kategorie**: Gruppiert ähnliche Produkte, um die Navigation für den Benutzer zu erleichtern. Diese enthält eine UUID, einen Namen und eine Liste an Kategorien.

## 1.2 Verwendung taktischer Muster

- **Entities**
- **Value Objects**
- **Aggregates**
- **Repositories**
- **Domain Services**

### 1.2.1 Entity: User
```java
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(nullable = false, unique = true)
    private String username;

    @Embedded
    private Address address;
 
    @Column(nullable = false, unique = true)
    private Email email;

    @Column(nullable = false)
    private String password;

    
    public User() {}

    public User(String username, Email email, String password, Address address) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.address = address;
    }

    public UUID getId() { return id; }
    public String getUsername() { return username; }
    public Email getEmail() { return email; }
    public String getPassword() { return password; }
    public Address getAddress() { return address;}

    public void setId(UUID id) {this.id = id;}
    public void setUsername(String username) {this.username = username;}
    public void setEmail(Email email) {this.email = email;}
    public void setPassword(String password) {this.password = password;}
    public void setAddress(Address address) { this.address = address;}  
}
```
- User ist eine **Entity**, weil sie eine eindeutige Identität (UUID id) besitzt.
- Zwei User-Objekte mit demselben Benutzernamen oder derselben E-Mail sind nicht zwangsläufig dieselbe Entität.
- User-Daten wie Adresse oder E-Mail können sich über die Zeit ändern.

### 1.2.2 Value Object: Address
```java
@Data
@Embeddable
public class Address {
    private String street;
    private String city;
    private String postalCode;
    private String country;
    
    protected Address() {}

    public Address(String street, String city, String postalCode, String country) {
        this.street = street;
        this.city = city;
        this.postalCode = postalCode;
        this.country = country;
    }

    public String getStreet() {return street;}
    public String getCity() {return city;}
    public String getPostalCode() {return postalCode;}
    public String getCountry() {return country;}
}
```
- Address ist ein **Value Object**, weil es keine eigene Identität hat.
- Zwei Address-Objekte mit denselben Werten sind austauschbar.
- Unveränderlichkeit: Einmal erstellt, sollte eine Adresse nicht verändert werden, sondern durch eine neue ersetzt werden.

### 1.2.3 Aggregate: User als Root
- User ist die **Aggregate Root**, da es die Verwaltung von Address als eingebettetes Value Object übernimmt.
- Änderungen an der Adresse erfolgen nur über die User-Entität, um Konsistenz zu gewährleisten.

### 1.2.4 Repository: UserRepository
#### UserRepository als Schnittstelle
```java
public interface UserRepository{
    Optional<User> findByEmail(Email email);
    Optional<User> findByUsername(String username);
    Optional<User> findById(UUID id);
    List<User> findAll();
    User save(User user);
    void deleteById(UUID id);
}
```
#### JpaUserRepository als Repository-Implementierung
```java
@Repository
public interface JpaUserRepository extends JpaRepository<User, UUID>, UserRepository {
    Optional<User> findByEmail(String email);
    Optional<User> findByUsername(String username);
}
```
- UserRepository definiert die Schnittstelle für den Datenzugriff auf die User-Entität.
- JpaUserRepository implementiert die Datenbankinteraktion mit Spring Data JPA und kapselt SQL-Logik.
- Dadurch bleibt die Domäne von technischen Details getrennt.

### 1.2.5 Domain Service: UserService
```java
@Service
public class UserService {

    private UserRepository userRepository;

    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public UserDTO registerUser(User user) {
        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            throw new UserRegistrationException("Diese E-Mail wird bereits verwendet.");
        }
        if (userRepository.findByUsername(user.getUsername()).isPresent()) {
            throw new UserRegistrationException("Dieser Benutzername wird bereits verwendet.");
        }
        return UserMapper.toDTO(userRepository.save(user)); 
    }

    public UserDTO getUserById(UUID id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("Benutzer nicht gefunden"));
        return UserMapper.toDTO(user);
    }

    public UserDTO updateUser(UUID id, User updatedUser) {
        User existingUser = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("Benutzer nicht gefunden"));

        existingUser.setUsername(updatedUser.getUsername());
        existingUser.setEmail(updatedUser.getEmail());

        return UserMapper.toDTO(userRepository.save(existingUser));
    }

    public void deleteUser(UUID id) {
        userRepository.deleteById(id);
    }
}
```
- UserService ist ein **Domain Service**, weil er geschäftslogische Regeln zur Benutzerregistrierung kapselt.
- Trennt Geschäftslogik von der User-Entität, indem es Prüfungen wie z. B. doppelte E-Mail ausführt.
- Nutzt UserRepository, um die Datenbankabfragen zu abstrahieren.

# 2. Clean Architecture

## 2.1 Schichtarchitektur und Begründung

Das Projekt ist in folgende Schichten unterteilt:

### **Plugins-Schicht (0-plugins)**: 
- Enthält die Main Methode der Anwendung.
- Sie dient als Einstiegspunkt der Anwendung und initialisiert alle notwendigen Framework-Komponenten (Spring Boot).

### **Adapter-Schicht (1-adapters)**: 
- Verantwortlich für die Kapselung von Infrastruktur- und Schnittstellenlogik.
- Beinhaltet wichtige Komponenten wie:
  - Controller für den API-Zugriff z. B. REST-Endpunkte.
  - JPA Repositories, die als Brücke zur Datenbank fungieren.
  - Security-Konfigurationen z. B. JWT-basierte Authentifizierung, CORS-Konfiguration für das Frontend.
- Diese Trennung stellt sicher, dass die Geschäftslogik unabhängig von der Datenbank oder spezifischen externen Abhängigkeiten bleibt.

### **Application-Schicht (2-application)**: 
- Definiert Use Cases, also spezifische Geschäftsprozesse der Anwendung.
- Implementiert als*eigenständige Klassen, die das Verhalten der Anwendung steuern.

Beispiel: **LoginUseCase**
```java
@Component
public class LoginUseCase {

    private final AuthService authService;

    public LoginUseCase(AuthService authService) {
        this.authService = authService;
    }

    public AuthResponseDTO execute(AuthRequestDTO authRequestDTO) {
        var loginResult = authService.login(authRequestDTO.getEmail(), authRequestDTO.getPassword());
        return AuthMapper.toDTO(loginResult.getUser(), loginResult.getToken());
    }
}
```
### **Domain-Schicht (3-domain)**: 
- Enthält zentrale Geschäftslogik, Entitäten, Value Objects, Services und Repositories.
- Die Domain-Schicht hat keine Abhängigkeit zu externen Frameworks, was sie besonders stabil und langlebig macht.

### **Abstraktionscode-Schicht (4-abstractioncode)**
- Diese Schicht ist für wiederverwendbare, domänenunabhängige Logik gedacht.
- Diese kapselt wiederverwendbare, domänenunabhängige Logik.
- In der aktuellen Implementierung ist sie leer. 
Kapselt wiederverwendbare, domänenunabhängige Logik. Ist in meinem Fall leer.

## 2.2 Implementierte Schichten
- Alle fünf Schichten wurden implementiert.
- Plugins (0-plugins) enthält nur die Main-Methode und übernimmt keine Geschäftslogik.
- Abstraktionscode (4-abstractioncode) ist aktuell leer, kann aber später für übergreifende Funktionen verwendet werden.

# 3. Programming Principles

## 3.1 Analyse und Begründung für Programming Principles

### **1. Single-Responsibility Principle (SRP) – SOLID**
**Definition:**
- Das **Single-Responsibility Principle (SRP)** besagt, dass jede Klasse nur eine einzige Verantwortlichkeit haben sollte und nur einen Grund für Änderungen haben darf.

Beispiel aus dem Code: **UserService.java**
```java
@Service
public class UserService {

    private UserRepository userRepository;

    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public UserDTO registerUser(User user) {
        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            throw new UserRegistrationException("Diese E-Mail wird bereits verwendet.");
        }
        if (userRepository.findByUsername(user.getUsername()).isPresent()) {
            throw new UserRegistrationException("Dieser Benutzername wird bereits verwendet.");
        }
        return UserMapper.toDTO(userRepository.save(user)); 
    }
}
```
**Begründung:**
- Die Klasse UserService ist ausschließlich für die Geschäftslogik der Benutzerverwaltung zuständig (Registrierung, Abrufen, Aktualisierung, Löschen).
- Sie kümmert sich nicht um Datenbankzugriffe oder API-Aufrufe, diese sind über Repositories und Controller ausgelagert.
- Dadurch bleibt der Code modular und leicht erweiterbar.

---

### **2. Open-Closed Principle (OCP) – SOLID**
**Definition:**
- Das **Open-Closed Principle (OCP)** besagt, dass eine Software offen für Erweiterung, aber geschlossen für Modifikation sein sollte.

Beispiel aus dem Code: **JwtUtil.java**
```java
@Component
public class JwtUtil implements JwtProvider {

    private final SecretKey SECRET_KEY = Keys.hmacShaKeyFor("MySuperSecureSecretKeyThatIsLongEnoughForHS256".getBytes(StandardCharsets.UTF_8));

    @Override
    public String generateToken(String username) {
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60))
                .signWith(SECRET_KEY)
                .compact();
    }

    @Override
    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }
    
    @Override
    public boolean validateToken(String token, String username) {
        return (username.equals(extractUsername(token)) && !isTokenExpired(token));
    }

    private boolean isTokenExpired(String token) {
        return extractClaim(token, Claims::getExpiration).before(new Date());
    }

    private <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(SECRET_KEY)
                .build()
                .parseClaimsJws(token)
                .getBody();
        return claimsResolver.apply(claims);
    }
}
```
und der dazu gehörige **JwtProvider.java**

```java
public interface JwtProvider {
    String generateToken(String username);
    String extractUsername(String token);
    boolean validateToken(String token, String username);
}
```
**Begründung:**
- JwtUtil implementiert das JwtProvider Interface, was bedeutet, dass neue Token-Mechanismen hinzugefügt werden können, ohne die bestehende Implementierung zu ändern.
- Falls später in anderer Token-Mechanismus (z. B. OAuth, HMAC, RSA) verwendet werden soll, kann eine neue Implementierung von JwtProvider erstellt werden, anstatt JwtUtil zu modifizieren.
- Die bestehende Klasse JwtUtil bleibt unverändert (geschlossen für Modifikation), während durch neue Implementierungen von JwtProvider weitere Authentifizierungsmechanismen hinzugefügt werden können (offen für Erweiterung).
- Dies verhindert harte Abhängigkeiten von JwtUtil in anderen Klassen und erleichtert das Austauschen der Token-Strategie, falls sich Sicherheitsanforderungen ändern.

---

### **3. High Cohesion – GRASP**
**Definition:**
- Das Prinzip der **High Cohesion** besagt, dass eine Klasse thematisch zusammenhängende Aufgaben enthalten sollte, um eine hohe Wiederverwendbarkeit und Wartbarkeit zu gewährleisten.

Beispiel aus dem Code: **AuthService.java**
```java
@Service
public class AuthService {
    private UserRepository userRepository;
    private JwtProvider jwtProvider;

    public AuthService(UserRepository userRepository, JwtProvider jwtProvider){
        this.userRepository = userRepository;
        this.jwtProvider = jwtProvider;
    }

    public LoginResult login(Email email, String password) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException("Benutzer nicht gefunden."));

        if (!user.getPassword().equals(password)) {
            throw new InvalidCredentialsException("Falsche Anmeldedaten. Überprüfen Sie Ihr Passwort oder die E-Mail-Adresse.");
        }

        String token = jwtProvider.generateToken(user.getId().toString()); 
        return new LoginResult(user, token);
    }
}
```
**Begründung:**
- Die AuthService-Klasse konzentriert sich nur auf Authentifizierungslogik, indem sie Benutzerdaten überprüft und JWT-Tokens generiert.
- Andere Aufgaben (z. B. Registrierung oder JWT Token Generierung) sind in anderen Klassen gekapselt.


---

### **4. DRY (Don’t Repeat Yourself)**
**Definition:**
- Das **DRY-Prinzip** besagt, dass duplizierter Code vermieden werden soll, indem wiederverwendbare Komponenten geschaffen werden.

Beispiel aus dem Code: **AuthMapper.java**
```java
public class AuthMapper {
    public static AuthResponseDTO toDTO(User user, String token) {
        return new AuthResponseDTO(user.getId(), token);
    }
}
```
**Begründung:**
- AuthMapper verhindert, dass die Mapping-Logik mehrfach in verschiedenen Klassen implementiert wird.
- Statt in jedem Service oder Controller `new AuthResponseDTO(user.getId(), token)` zu schreiben, wird die zentrale `MethodetoDTO()` genutzt.
- Falls das DTO-Feld später geändert werden muss, ist die Änderung nur an einer Stelle notwendig.

---

### **5. Low Coupling – GRASP**
**Definition:**
- Das **Low Coupling Prinzip** besagt, dass Klassen so wenig Abhängigkeiten wie möglich haben sollten, um Änderungen in einer Klasse nicht auf viele andere Klassen auswirken zu lassen.

Beispiel aus dem Code: **OrderRepository.java**
```java
public interface OrderRepository{
    List<Order> findByUserId(UUID userId);
    Optional<Order> findById(UUID id);
    List<Order> findAll();
    Order save(Order order);
    void deleteById(UUID uuid);
}
```
**Begründung:**
- OrderRepository reduziert die Abhängigkeit von konkreten Implementierungen der Datenbanklogik.
- Jede andere Klasse, die OrderRepository verwendet, kann darauf zugreifen, ohne direkt von der konkreten Persistenztechnologie abhängig zu sein.
- Falls die Datenbank später geändert wird (z. B. von Postgres auf NoSQL), bleibt die API der Anwendung unverändert.
- Im generrellen zielt jedoch Clean Architecture darauf ab Abhänigkeiten zu reduzieren






