import axios from 'axios';

const axiosConfig = {
	baseURL: 'http://localhost:3001',
};

const instance = axios.create(axiosConfig);

export const getMypageInfo = async (userId) => {
	try {
		const result = await instance.get(`/users/${userId}`);
		return result.data;
	} catch (err) {
		console.log(err);
	}
};
