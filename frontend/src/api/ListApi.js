import axios from 'axios';

const axiosConfig = {
	baseURL: 'http://localhost:3001',
};

const instance = axios.create(axiosConfig);

export const getList = async () => {
	try {
		const result = await instance.get(`/top
		`);
		return result.data;
	} catch (err) {
		console.log(err);
	}
};
