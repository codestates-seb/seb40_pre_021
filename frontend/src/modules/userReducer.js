import { createSlice } from '@reduxjs/toolkit'; // 1.
// 2.
export const userSlice = createSlice({
	name: 'user', // 3.
	initialState: {
		// 4.
		isLogin: false,
		userInfo: {},
	},

	reducers: {
		// 5.
		loginSuccess: (state, actions) => {
			state.isLogin = true;
			state.userInfo = actions.payload;
		},
		logoutSuccess: (state) => {
			state.isLogin = false;
			state.userInfo = {};
		},
	},
});

export const { loginSuccess, logoutSuccess } = userSlice.actions; // 7.
export default userSlice.reducer; // 8.
export const selectIsLogin = (state) => state.user.isLogin; // 9.
export const selectUserInfo = (state) => state.user.userInfo; // 10.
