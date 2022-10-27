import styled from 'styled-components';
import { MdCake } from 'react-icons/md';
import { AiOutlineClockCircle } from 'react-icons/ai';
import { FaRegCalendarAlt } from 'react-icons/fa';

const UserInfo = ({ date }) => {
	return (
		<Container>
			<Box>
				<SignupIcon />
				<InfoText>Members for {date}</InfoText>
			</Box>
			<Box>
				<ClockIcon />
				<InfoText>Last seen {date}</InfoText>
			</Box>
			<Box>
				<CalendarIcon />
				<InfoText>Visited 3 days, 3 consecutive</InfoText>
			</Box>
		</Container>
	);
};

export default UserInfo;

const Container = styled.div`
	display: flex;
	flex-wrap: wrap;
`;

const Box = styled.div`
	display: flex;
	margin: 4px;
`;

const SignupIcon = styled(MdCake)`
	width: 16px;
	height: 16px;
	color: #9099a1;
`;

const ClockIcon = styled(AiOutlineClockCircle)`
	width: 16px;
	height: 16px;
	color: #9099a1;
`;

const CalendarIcon = styled(FaRegCalendarAlt)`
	width: 16px;
	height: 16px;
	color: #9099a1;
`;

const InfoText = styled.span`
	color: #6a737c;
	margin: 2px 4px;
	font-size: 14px;
	font-weight: 600;
`;
