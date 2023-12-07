import axios from 'axios';

// const BASE_URL = 'https://stuiis.cms.gre.ac.uk/COMP1424CoreWS/comp1424cw';
const BASE_URL = 'http://192.168.1.97:8080'; 
const userId = 'group_29';

export async function getClassInstances() {
    const response = await axios.post(`${BASE_URL}/GetInstances`, {userId, b2: 'Submit'});
    return response.data;
}

export async function submitBookings(bookings) {
    const data = {
        b3: 'Submit',
        jsonPayload: {
            userId: userId,
            bookingList: bookings
        }
    };

    const response = await axios.post(`${BASE_URL}/SubmitBookings`, data);
    return response.data;
}
