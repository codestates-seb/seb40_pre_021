import axios from 'axios';
import { root } from './root';

const axiosConfig = {
	baseURL: root,
};

const instance = axios.create(axiosConfig);

export const getList = async (data) => {
	let url = '/questions';
	if (data.q !== undefined) {
		url = '/search';
	}
	// console.log(url, data);
	try {
		const result = await instance.get('/questions', { params: data });
		return result.data;
	} catch (err) {
		console.log(err);
	}
};
