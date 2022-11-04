import styled from 'styled-components';
import Empty from '../../../assets/images/Mypage/SavesEmpty.png';

const EmptySavesBox = () => {
	return (
		<Container>
			<Img src={Empty} width={250} height={210} />
			<Text>You have no saved items</Text>
		</Container>
	);
};

export default EmptySavesBox;

const Container = styled.div`
	max-width: calc(var(100rem) * 4);
	padding: 48px;
	color: #6a737c;
	text-align: center;
	margin-left: auto;
	margin-right: auto;
`;

const Img = styled.img`
	margin: 24px;
	vertical-align: bottom;
	overflow: hidden;
`;

const Text = styled.p`
	font-size: 13px;
	margin-bottom: 12px;
	margin-top: 0;
`;
