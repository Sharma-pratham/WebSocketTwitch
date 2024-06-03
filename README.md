# WebSocketTwitch
 
This file contains information on all the files in this project what it does and what information each file holds

Spring Boot project

Redis
I have used a docker image of redis (version 7.0.15-alpine3.20) to run my message queue on it. Details can be found in application.properties file.

PostgreSQL
You can find all the scripts I have used in the DatabaseScripts file under resources

Swagger

# Folder Breakdown

## ConfigurationFolder

### SecurityConfig.java

This file configures the security for your Spring Boot application. It sets up OAuth2, JWT, and role-based access control.

Class: SecurityConfig

Annotations:
@Configuration: Indicates that this class contains Spring configuration.
@EnableWebSecurity: Enables Spring Security's web security support.

Fields:
JwtTokenProvider jwtTokenProvider: Provides JWT token utilities.

Methods:
configure(HttpSecurity http): Configures the HTTP security including disabling CSRF, setting session management to stateless, defining URL access rules, and adding OAuth2 login and JWT filter.
passwordEncoder(): Defines the password encoder bean using BCryptPasswordEncoder.
configure(AuthenticationManagerBuilder auth): Configures the authentication manager with the user details service and password encoder.

### JwtTokenProvider.java

This file handles the creation and validation of JWT tokens.

Class: JwtTokenProvider

Annotations:

@Component: Indicates that this class is a Spring component.
Fields:

String secret: Secret key for signing the JWT tokens.
long expiration: Expiration time for the JWT tokens.
Methods:

generateToken(UserDetails userDetails): Generates a JWT token based on user details.
validateToken(String token, UserDetails userDetails): Validates the JWT token.
getUsernameFromToken(String token): Extracts the username from the token.
getExpirationDateFromToken(String token): Extracts the expiration date from the token.
getClaimFromToken(String token, Function<Claims, T> claimsResolver): Extracts claims from the token.
getAllClaimsFromToken(String token): Extracts all claims from the token.
isTokenExpired(String token): Checks if the token is expired.

### JwtTokenFilter.java

This file is a filter that intercepts requests to check for a valid JWT token.

Class: JwtTokenFilter
Annotations:
@Component: Indicates that this class is a Spring component.
Fields:
JwtTokenProvider jwtTokenProvider: Provides JWT token utilities.
UserDetailsService userDetailsService: Service to load user details.
Methods:
doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain): Intercepts requests, extracts the JWT token, validates it, and sets the authentication in the security context if valid.
getJwtFromRequest(HttpServletRequest request): Extracts the JWT token from the request header.

### WebSocketConfig.java

This file configures WebSocket messaging for your Spring Boot application.

Class: WebSocketConfig

Annotations:

@Configuration: Indicates that this class contains Spring configuration.
@EnableWebSocketMessageBroker: Enables WebSocket message handling, backed by a message broker.
Methods:

configureMessageBroker(MessageBrokerRegistry config): Configures the message broker with destination prefixes for topics and queues.
registerStompEndpoints(StompEndpointRegistry registry): Registers STOMP endpoints and configures SockJS fallback options.

### RedisConfig.java

This file configures Redis for your Spring Boot application.

Class: RedisConfig

Annotations:
@Configuration: Indicates that this class contains Spring configuration.

Methods:
redisTemplate(RedisConnectionFactory redisConnectionFactory): Configures the RedisTemplate with a connection factory and a generic serializer.

### SwaggerConfig.java

This file configures Swagger for API documentation.

Class: SwaggerConfig

Annotations:

@Configuration: Indicates that this class contains Spring configuration.
@EnableSwagger2: Enables Swagger 2 for API documentation.
Methods:

api(): Configures the Docket bean for Swagger, setting the base package for API scanning and enabling documentation for all paths.

## Model

### ChatMessage.java

This file defines the model for chat messages.

Class: ChatMessage

Fields:
MessageType type: Enum for the type of message (CHAT, JOIN, LEAVE).
String content: Content of the message.
String sender: Sender of the message.
Enum: MessageType
CHAT: Regular chat message.
JOIN: Message indicating a user has joined.
LEAVE: Message indicating a user has left.

## Controller

### WebSocketController.java

This file is a controller that handles WebSocket messages.

Class: WebSocketController

Annotations:

@Controller: Indicates that this class is a Spring MVC controller.
Fields:

SimpMessagingTemplate messagingTemplate: Template for sending messages.
Methods:

sendMessage(ChatMessage message): Handles chat messages sent to /chat.sendMessage and broadcasts them to the /topic/public destination.
addUser(ChatMessage message): Handles user joining messages sent to /chat.addUser and broadcasts them to the /topic/public destination.