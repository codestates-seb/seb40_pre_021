import userReducer from '../modules/userReducer';
import { configureStore, combineReducers } from '@reduxjs/toolkit';
import {
	persistStore,
	persistReducer,
	FLUSH,
	REHYDRATE,
	PAUSE,
	PERSIST,
	PURGE,
	REGISTER,
} from 'redux-persist';
import storage from 'redux-persist/lib/storage';
import logger from 'redux-logger';

const persistConfig = {
	key: 'root',
	version: 1,
	storage,
};

//reducer 합치는 부분
const rootReducer = combineReducers({
	//추가되는 reducer
	user: userReducer,
});

const persistedReducer = persistReducer(persistConfig, rootReducer);

const store = configureStore({
	reducer: persistedReducer,
	middleware: (getDefaultMiddleware) =>
		getDefaultMiddleware({
			serializableCheck: {
				ignoredActions: [FLUSH, REHYDRATE, PAUSE, PERSIST, PURGE, REGISTER],
			},
			// 		}).concat(logger), // logger: 테스트를 위해 쓰임 주석 풀면 console에 store 동작 찍힘
			// });
		}),
});
export const persistor = persistStore(store);

export default store;
