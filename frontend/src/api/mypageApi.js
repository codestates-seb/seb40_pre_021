import axios from 'axios';
import { root } from './root';

const axiosConfig = {
	baseURL: root,
};

const instance = axios.create(axiosConfig);

export const getMypageUserInfo = async () => {
	try {
		const result = await instance.get(`/user/user-info`);
		return result.data;
	} catch (err) {
		console.log(err);
	}
};

export const getMypageUserAnswer = async () => {
	try {
		const result = await instance.get(`/user/answer`);
		return result.data;
	} catch (err) {
		console.log(err);
	}
};

export const getMypageUserQuestion = async () => {
	try {
		const result = await instance.get(`/user/question`);
		return result.data;
	} catch (err) {
		console.log(err);
	}
};

export const getMypageUserTag = async () => {
	try {
		const result = await instance.get(`/user/tag`);
		return result.data;
	} catch (err) {
		console.log(err);
	}
};

export const getMypageUserBookmark = async () => {
	try {
		const result = await instance.get(`/user/bookmark`);
		return result.data;
	} catch (err) {
		console.log(err);
	}
};
