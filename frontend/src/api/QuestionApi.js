import axios from 'axios';

const axiosConfig = {
	baseURL: 'http://localhost:3001',
};

const instance = axios.create(axiosConfig);

export const getQuestion = async () => {
	try {
		const result = await instance.get(`/data`);
		return result.data;
	} catch (err) {
		console.log(err);
		return err;
	}
};

export const ask = async (data) => {
	try {
		const result = await instance.post('/question/ask', data);
		return result.data;
	} catch (err) {
		console.log(err);
		return err;
	}
};

export const answer = async (data) => {
	try {
		const result = await instance.post('/questions/{questionId}/answer', data);
		return result.data;
	} catch (err) {
		console.log(err);
		return err;
	}
};
export const comment = async (data) => {
	try {
		const result = await instance.post('/questions/{questionId}/comment', data);
		return result.data;
	} catch (err) {
		console.log(err);
		return err;
	}
};

export const upVoteForQ = async (data) => {
	try {
		const result = await instance.put('/questions/{questionId}/up', data);
		return result.data;
	} catch (err) {
		console.log(err);
		return err;
	}
};

export const downVoteForQ = async (data) => {
	try {
		const result = await instance.put('/questions/{questionId}/down', data);
		return result.data;
	} catch (err) {
		console.log(err);
		return err;
	}
};

export const upVoteForA = async (data) => {
	try {
		const result = await instance.put('/answers/{answerId}/up', data);
		return result.data;
	} catch (err) {
		console.log(err);
		return err;
	}
};

export const downVoteForA = async (data) => {
	try {
		const result = await instance.put('/answers/{answerId}/down', data);
		return result.data;
	} catch (err) {
		console.log(err);
		return err;
	}
};

export const bookmarkQ = async (data) => {
	try {
		const result = await instance.post('/bookmark/{questionId}', data);
		return result.data;
	} catch (err) {
		console.log(err);
		return err;
	}
};

export const bookmarkA = async (data) => {
	try {
		const result = await instance.post('/bookmark/{answerId}', data);
		return result.data;
	} catch (err) {
		console.log(err);
		return err;
	}
};

export const bookmarkDelQ = async (data) => {
	try {
		const result = await instance.post('/bookmark/{questionid}', data);
		return result.data;
	} catch (err) {
		console.log(err);
		return err;
	}
};

export const bookmarkDelA = async (data) => {
	try {
		const result = await instance.post('/bookmark//answer/{answerid}', data);
		return result.data;
	} catch (err) {
		console.log(err);
		return err;
	}
};

export const choosed = async (data) => {
	try {
		const result = await instance.put(
			'/questions/{questionid}/answer/{answerid}/selection',
			data,
		);
		return result.data;
	} catch (err) {
		console.log(err);
		return err;
	}
};
