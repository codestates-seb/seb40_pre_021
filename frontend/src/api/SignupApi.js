import axios from 'axios';

const axiosConfig = {
	baseURL: 'http://localhost:3001',
};

const instance = axios.create(axiosConfig);

//dev : signup
//real : users/signup
export const Signup = async (data) => {
	try {
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
