import axios from 'axios';

const axiosConfig = {
	baseURL: 'http://localhost:3001',
};

const instance = axios.create(axiosConfig);

export const getList = async (data) => {
	try {
		const result = await instance.get(
			`/questions
		`,
			data,
		);
		return result.data;
	} catch (err) {
		console.log(err);
	}
};
