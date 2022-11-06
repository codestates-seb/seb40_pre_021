import { useState } from 'react';

const useTag = (initial = []) => {
	const [tags, setTags] = useState(initial);

	function removeTag(index) {
		setTags(tags.filter((el, idx) => idx !== index));
	}

	return [tags, setTags, removeTag];
};

export default useTag;
