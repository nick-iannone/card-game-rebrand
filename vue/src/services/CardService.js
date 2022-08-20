import axios from 'axios';

export default {

    getAllCards() {
        return axios.get('/cards');
    },

    getAllContracts() {
        return axios.get('/contracts');
    },

    getDecksByUsername(username) {
        return axios.get(`/decks/${username}`);
    },

    addNewDeck(deck) {
        return axios.post('/decks', deck);
    },

    updateFavorite(deckID, isFavorite) {
        return axios.post(`/decks/${deckID}/${isFavorite}`);
    }
}