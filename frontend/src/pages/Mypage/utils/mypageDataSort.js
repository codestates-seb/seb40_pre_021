const mypageDataSort = (name, data, tag = false) => {
	let newData = [];
	switch (name) {
		case 'Score':
			if (tag) {
				newData = [...data].sort((a, b) => b.tagCount - a.tagCount);
			} else {
				newData = [...data].sort((a, b) => b.vote - a.vote);
			}
			break;
		case 'Newest':
			newData = [...data].sort(function compare(a, b) {
				const dateA = new Date(a.createdAt).getTime();
				const dateB = new Date(b.createdAt).getTime();
				if (dateA > dateB) return -1;
				if (dateA < dateB) return 1;
				return 0;
			});
			break;
		case 'Name':
			newData = [...data].sort(function compare(a, b) {
				if (a.title < b.title) return -1;
				if (a.title > b.title) return 1;
				return 0;
			});
			break;
		case 'Views':
			newData = [...data].sort(function compare(a, b) {
				if (a.views > b.views) return -1;
				if (a.views < b.views) return 1;
				return 0;
			});
			break;
	}
	return newData;
};

export default mypageDataSort;
