import { useState } from 'react';

const useCurrentTab = (tabs = []) => {
	const [curTab, setCurTab] = useState(tabs[0]);

	const changeCurrentTab = (name) => {
		let newCurTab = tabs.filter((tab) => tab.name === name);
		setCurTab(...newCurTab);
	};

	return [curTab, changeCurrentTab];
};

export default useCurrentTab;
