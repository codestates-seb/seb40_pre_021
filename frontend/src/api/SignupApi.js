import axios from 'axios';

const axiosConfig = {
	baseURL: 'http://localhost:3001',
};

const instance = axios.create(axiosConfig);

//dev : signup
//real : users/signup
//test : data.json 에 "signup": {} 추가
export const Signup = async (data) => {
	try {
		//test용
		data.accessToken = 'dd';
		/**************** */
		const result = await instance.post(
			`/signup
		`,
			data,
		);

		return result.data;
	} catch (err) {
		console.log(err);
	}
};
