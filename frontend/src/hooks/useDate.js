import { useEffect, useState } from 'react';
import timeForToday from '../utils/timeForToday';

function useDate(value, type) {
	const [date, setDate] = useState(value);

	useEffect(() => {
		if (type) {
			setDate(timeForToday(value, type));
		} else {
			setDate(timeForToday(value));
		}
	}, [value]);

	return [date];
}

export default useDate;
