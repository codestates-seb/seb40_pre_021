import styled from 'styled-components';
import { FaBirthdayCake } from 'react-icons/fa';
// import timeForToday from '../../utils/timeForToday';
const UserSignupInfo = ({ value }) => {
	return (
		<div>
			<FaBirthdayCake />
			<SignupInfo>Members for {value}</SignupInfo>
		</div>
	);
};

export default UserSignupInfo;

const SignupInfo = styled.span`
	color: #6a737c;
`;
