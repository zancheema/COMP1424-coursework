import axios from 'axios';

const BASE_URL = 'https://stuiis.cms.gre.ac.uk/COMP1424CoreWS/comp1424cw';
const userId = 'group_29';

export async function getClassInstances() {
    const data = new FormData();
    data.append('userId', userId);
    const response = await axios.get(`${BASE_URL}/GetInstances`, { data: data });
    return response.data;
}

export async function submitBookings(bookings) {
    const data = new FormData();

    const payload = {userId: userId, bookingList: bookings};
    data.append('jsonpayload', JSON.stringify(payload));

    data.append('b3', 'Submit');

    const response = await axios.get(`${BASE_URL}/SubmitBookings`, { data: data });
    return response.data;
}
