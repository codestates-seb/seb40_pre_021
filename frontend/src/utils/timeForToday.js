export default function timeForToday(value, type = '') {
	const today = new Date();
	const timeValue = new Date(value);

	const betweenTime = Math.floor(
		(today.getTime() - timeValue.getTime()) / 1000 / 60,
	);

	if (betweenTime < 1) return 'just before';
	if (betweenTime < 60) {
		return `${betweenTime} mins ${type}`;
	}

	const betweenTimeHour = Math.floor(betweenTime / 60);
	if (betweenTimeHour < 24) {
		return `${betweenTimeHour} hours ${type}`;
	}

	const betweenTimeDay = Math.floor(betweenTime / 60 / 24);
	if (betweenTimeDay < 30) {
		return `${betweenTimeDay} days ${type}`;
	}

	const betweenTimeMonth = Math.floor(betweenTime / 60 / 24 / 30);
	if (0 < betweenTimeMonth && betweenTimeMonth < 12) {
		return `${betweenTimeMonth} months ${type}`;
	}

	return `${Math.floor(betweenTimeDay / 365)} years ${type}`;
}
