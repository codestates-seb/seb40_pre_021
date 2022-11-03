import axios from 'axios';

const axiosConfig = {
	baseURL: 'http://localhost:3001',
};

const instance = axios.create(axiosConfig);

export const getList = async (data) => {
	let url = '/questions';
	if (data.tagName !== undefined) {
		url = '/questions/tagged';
	} else if (data.q !== undefined) {
		url = '/search';
	}
	console.log(url, data);
	try {
		const result = await instance.get('/questions', { params: data });
		return result.data;
	} catch (err) {
		console.log(err);
	}
};
