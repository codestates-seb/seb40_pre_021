import axios from 'axios';
import { root } from './root';

const axiosConfig = {
	baseURL: root,
};

const instance = axios.create(axiosConfig);

//test
//test logout은 아무 의미 없음 통신 후처리를 위해 만듬
export const Logout = async (data) => {
	try {
		//test
		// data = {};
		// const result = await instance.post(`/login`, data);
		//real
		const result = await instance.post(`/users/logout`, data);
		return result.data;
	} catch (err) {
		console.log(err);
	}
};

//real
// export const Logout = async (data) => {
// 	try {
// 		const result = await instance.delete(
// 			`/users/logout
// 		`,
// 			data,
// 		);
// 		return result.data;
// 	} catch (err) {
// 		console.log(err);
// 	}
// };
