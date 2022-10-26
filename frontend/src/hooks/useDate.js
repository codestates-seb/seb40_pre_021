import { useEffect, useState } from 'react';
import timeForToday from '../utils/timeForToday';

function useDate(value) {
	const [date, setDate] = useState(value);

	useEffect(() => {
		setDate(timeForToday(value));
	}, [value]);

	return [date];
}

export default useDate;
