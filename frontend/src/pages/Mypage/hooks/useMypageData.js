import { useEffect, useState } from 'react';
import {
	getMypageUserAnswer,
	getMypageUserBookmark,
	getMypageUserInfo,
	getMypageUserQuestion,
	getMypageUserTag,
} from '../../../api/mypageApi';

function useMypageData(category, value = []) {
	const [data, setData] = useState(value);

	useEffect(() => {
		switch (category) {
			case 'answer':
				getMypageUserAnswer().then((res) => {
					setData(res);
				});
				break;
			case 'question':
				getMypageUserQuestion().then((res) => {
					setData(res);
				});
				break;
			case 'tag':
				getMypageUserTag().then((res) => {
					setData(res);
				});
				break;
			case 'bookmark':
				getMypageUserBookmark().then((res) => {
					setData(res);
				});
				break;
			case 'userInfo':
				getMypageUserInfo().then((res) => {
					setData(res);
				});
				break;
		}
	}, [category]);

	return [data, setData];
}

export default useMypageData;
