Online Movie Booking System

Online Movie Booking System

Requirements
1. Actors
    - Admin :
        - manages movies and theaters.
        - onboard cinema, screens & seat arrangements.
        - add / update / delete movie & movie shows.
    - User : customer
2. multiple cinemas in the city
3. Movie might have multiple shows in a cenema hall
4. user search movie by
   city, title, language, genere, etc
5. user can view cinema & show times for a selected movie
6. seat selection
    - each seat category(platinum, gold, silver) has diferrent price
7. Book ticket
    - online
    - walk-in
8. payment
    - multiple payment modes
9. notification
    - booking confirmation - for both booking and canceled
    - reminder
    - new movie - might be out of scope

Out of scope
1. limiting movie booking only
2. security - authentication & Authorisation
3. movie reviews

NFRs
1. Consistency: no two user book the same seat.

------------------------------------------------------------------
Work flows
Admin -> Add movie -> assign screen & show times
USer -> search movie -> select seats -> book tickets -> payment -> booking confirmation

------------------------------------------------------------------
APIs
User registration
POST /v1/user/register {
name
email
phone
password
}
other non important APIs like get profile, update & login

Cinema
POST /v1/cenima {
cinema
address: {
area:
location: {
lat
long
}
city
state
pincode
}
}

GET /v1/cinemas?name=""&city=""&movie=""
[
{
name:
address:
shows: [
]
}
]

Movie
POST /v1/movie {
name
genre
cast []
releaseDate
}

Show
POST /v1/shows {
movieId
cinemaId
screenId
startTime
endTime
}

GET /v1/shows?movie=""
[
{
showId
showTime {start & end}
screen: {creenId, name}
}
]

GET /v1/shows/{showId}/seats [
{
seatId
seatNumber: A11
category: <platinum/gold/silver>
status: available / reserved
}
]

Search & booking APIs
get /v1/movies?name=""&city=""&cinema=""
[
{
movieId
name
cast: []
genre
}
]

POST /v1/bookings {
userId, showId, seatIds: []
}

payment
POST /v1/payments/initiate {
bookingId, gatewayUrl, status
}

POST /v1/payments/{paymentId}/verify {
transactionId, status, signature
}
ignoring other APIs lik refund

Notification
POST /v1/notify/bookings {
userId
type: {email: true,  sms: false, push: true}
message {title, msg}
}