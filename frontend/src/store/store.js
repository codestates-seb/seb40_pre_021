import userReducer from '../modules/userReducer';
import { configureStore } from '@reduxjs/toolkit';

// const store = createStore(reducer, composeWithDevTools(applyMiddleware(thunk)));
const store = configureStore({
	reducer: {
		user: userReducer,
	},
});

export default store;
