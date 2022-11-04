import Ask from './Ask';
import { getThreadInfo } from '../api/EditApi';
import { useState, useEffect } from 'react';

const Edit = () => {
	const [thread, setThread] = useState('');
	useEffect(() => {
		getThreadInfo().then((res) => setThread(res), []);
	});
	const editTitle = thread.title;
	const editBody = thread.contents;
	const editTag = thread.tags;
	return <Ask editTitle={editTitle} editBody={editBody} editTag={editTag} />;
};

export default Edit;
