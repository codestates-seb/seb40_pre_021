import axios from 'axios';

const axiosConfig = {
	baseURL: 'http://localhost:3001',
};

const instance = axios.create(axiosConfig);

export const getQuestion = async (questionid) => {
	try {
		const result = await instance.get(`/question`);
		return result.data;
	} catch (err) {
		console.log(err);
	}
};
