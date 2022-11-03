import { createSlice } from '@reduxjs/toolkit'; // 1.
// 2.
export const tagsSlice = createSlice({
	name: 'tags', // 3.
	initialState: {
		// 4.
		tags: [],
	},

	reducers: {
		// 5.
		setTags: (state, actions) => {
			state.tags = actions.payload;
		},
	},
});

export const { setTags } = tagsSlice.actions; // 7.
export default tagsSlice.reducer; // 8.
export const selectTags = (state) => state.tags.tags; // 9.
