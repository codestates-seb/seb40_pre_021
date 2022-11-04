import { useEffect, useState } from 'react';

const useSortButton = (data = []) => {
	const [menu, setMenu] = useState([]);

	const handleMenuChange = (id) => {
		let newMenu = menu.map((item) =>
			item.id === id ? { ...item, clicked: true } : { ...item, clicked: false },
		);
		setMenu(newMenu);
	};

	useEffect(() => {
		setMenu(data);
	}, [data]);

	return [menu, handleMenuChange];
};

export default useSortButton;
