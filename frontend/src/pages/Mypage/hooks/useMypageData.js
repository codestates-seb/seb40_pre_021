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
					if (res.length > 1) {
						let sortData = res.sort((a, b) => b.vote - a.vote);
						setData(sortData);
					} else {
						setData(res);
					}
				});
				break;
			case 'question':
				getMypageUserQuestion().then((res) => {
					const { data, pageInfo } = res;
					if (data.length > 1) {
						let sortData = data.sort((a, b) => b.vote - a.vote);
						setData(sortData);
					} else {
						setData(data);
					}
				});
				break;
			case 'tag':
				getMypageUserTag().then((res) => {
					const { data, pageInfo } = res;
					if (data.length > 1) {
						let sortData = data.sort((a, b) => b.tagCount - a.tagCount);
						setData(sortData);
					} else {
						setData(data);
					}
				});
				break;
			case 'bookmark':
				getMypageUserBookmark().then((res) => {
					const { data, pageInfo } = res;
					if (data.length > 1) {
						let sortData = data.sort((a, b) => b.views - a.views);
						setData(sortData);
					} else {
						setData(data);
					}
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
