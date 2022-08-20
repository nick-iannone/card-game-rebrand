import axios from 'axios'

export default {
    sendGame(game) {
        return axios.post('/games', game);
    }
}