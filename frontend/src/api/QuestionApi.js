import instance from './root';

export const ask = async (data) => {
	try {
		const result = await instance.post('/questions/ask', data);
		return result.data;
	} catch (err) {
		console.log(err);
		return err;
	}
}; //질문 작성

export const getUserInfo = async () => {
	try {
		const result = await instance.get(`/user/info`);
		// const result = await instance.get(`/user`); //테스트용
		return result.data;
	} catch (err) {
		console.log(err);
		return err;
	}
}; //유저 인포 가져옴

export const getQuestion = async (data) => {
	try {
		const result = await instance.get(`/question/${data}`);
		// const result = await instance.get(`/data`); //테스트용
		return result.data;
	} catch (err) {
		console.log(err);
		return err;
	}
}; //질문 상세페이지 조회

export const commentQ = async (data) => {
	try {
		// const result = await instance.post('/data', data); //테스트용
		const result = await instance.post(
			`/questions/question/${data.questionId}/comment`,
			JSON.stringify(data),
		);
		return result.data;
	} catch (err) {
		console.log(err);
		return err;
	}
}; //질문에 대한 댓글 작성

export const answer = async (data) => {
	try {
		// const result = await instance.post('/data', data); //test
		const result = await instance.post(
			`/questions/${data.questionId}/answer`,
			JSON.stringify(data),
		);
		return result.data;
	} catch (err) {
		console.log(err);
		return err;
	}
}; //답변 작성

export const commentA = async (data) => {
	try {
		// const result = await instance.post('/data', data); //test
		const result = await instance.post(
			`/questions/answer/${data.answerId}/comment`,
			JSON.stringify(data),
		);
		return result.data;
	} catch (err) {
		console.log(err);
		return err;
	}
}; //답변에 대한 댓글 작성
export const commentQDEL = async (data) => {
	try {
		const result = await instance.post('/questions/:questionId/comment', data);
		return result.data;
	} catch (err) {
		console.log(err);
		return err;
	}
}; //질문 댓글 삭제(임시)
export const commentADEL = async (data) => {
	try {
		const result = await instance.post('/answers/:answerId/comment', data);
		return result.data;
	} catch (err) {
		console.log(err);
		return err;
	}
}; //질문 댓글 삭제(임시)
export const upVoteForQ = async (data) => {
	try {
		const result = await instance.put(
			`/questions/${data.questionId}/like`,
			JSON.stringify(data),
		);
		return result.data;
	} catch (err) {
		console.log(err);
		return err;
	}
}; //질문 추천

export const downVoteForQ = async (data) => {
	try {
		const result = await instance.put(
			`/questions/${data.questionId}/like`,
			JSON.stringify(data),
		);
		return result.data;
	} catch (err) {
		console.log(err);
		return err;
	}
}; //질문 비추천

export const upVoteForA = async (data) => {
	try {
		const result = await instance.put(
			`/answers/${data.answerId}/like`,
			JSON.stringify(data),
		);
		return result.data;
	} catch (err) {
		console.log(err);
		return err;
	}
}; //답변 추천

export const downVoteForA = async (data) => {
	try {
		const result = await instance.put(
			`/answers/${data.answerId}/like`,
			JSON.stringify(data),
		);
		return result.data;
	} catch (err) {
		console.log(err);
		return err;
	}
}; //답변 비추천

export const bookmarkQ = async (data) => {
	try {
		const result = await instance.post(
			`/questions/bookmark/${data.questionId}`,
			JSON.stringify(data),
		);
		return result.data;
	} catch (err) {
		console.log(err);
		return err;
	}
}; //질문 북마크

export const bookmarkA = async (data) => {
	try {
		const result = await instance.post(
			`/questions/bookmark/${data.questionId}/${data.answerId}`,
			JSON.stringify(data),
		);
		return result.data;
	} catch (err) {
		console.log(err);
		return err;
	}
}; //답변 북마크

export const bookmarkDelQ = async (data) => {
	try {
		const result = await instance.delete(
			`/questions/bookmark/${data.questionId}`,
			JSON.stringify(data),
		);
		return result.data;
	} catch (err) {
		console.log(err);
		return err;
	}
}; //질문 북마크 삭제

export const bookmarkDelA = async (data) => {
	try {
		const result = await instance.delete(
			`/questions/bookmark/${data.questionId}/${data.answerId}`,
			JSON.stringify(data),
		);
		return result.data;
	} catch (err) {
		console.log(err);
		return err;
	}
}; //답변 북마크 삭제

export const choose = async (data) => {
	try {
		const result = await instance.put(
			`questions/question/${data.questionId}/adopt/${data.answerId}`,
			JSON.stringify(data),
		);
		return result.data;
	} catch (err) {
		console.log(err);
		return err;
	}
}; //답변 채택
