import axios from 'axios';

// export const root = process.env.REACT_APP_STACK_SERVER_TEST;
const root = process.env.REACT_APP_STACK_SERVER;

const axiosConfig = {
	baseURL: root,
};

const instance = axios.create(axiosConfig);

instance.defaults.withCredentials = true; // withCredentials 전역 설정

export default instance;
