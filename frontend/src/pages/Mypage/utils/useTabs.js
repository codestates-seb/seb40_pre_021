import { useState } from 'react';

const useTabs = (data = []) => {
	const [tabs, setTabs] = useState(data);

	const handleTabChange = (id) => {
		let newTabs = tabs.map((tab) =>
			tab.id === id ? { ...tab, clicked: true } : { ...tab, clicked: false },
		);
		setTabs(newTabs);
	};

	return [tabs, handleTabChange];
};

export default useTabs;
