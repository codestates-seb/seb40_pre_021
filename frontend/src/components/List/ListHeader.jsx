import { useSelector } from 'react-redux';
import { useNavigate } from 'react-router-dom';
import styled from 'styled-components';
import { selectIsLogin } from '../../modules/userReducer';
import Button from '../common/Button';
import Tab from '../common/Tab';

const ListHeaderStyle = styled.div`
	padding: 24px;

	section {
		padding: 12px;
		display: flex;
		align-items: center;
		:first-child {
			padding: 0;
		}
	}
`;

const Section = styled.section`
	justify-content: space-between;

	.title {
		font-size: 27px;
		font-weight: 400;
	}
`;
const BottomSection = styled.section`
	justify-content: ${(props) => (props.filter ? 'space-between' : 'flex-end')};

	.questions {
		font-size: 1.30769231rem;
		font-weight: 400;
	}
`;

const TabContainer = styled.div`
	display: flex;
`;

const ListHeader = ({
	title,
	Detail,
	filter,
	tabList,
	questionCount,
	tab,
	setTab,
	q,
}) => {
	const isLogin = useSelector(selectIsLogin);
	const navigate = useNavigate();
	return (
		<ListHeaderStyle>
			<Section>
				<div className="title">
					{q !== undefined ? `Search Results` : title}
				</div>
				<Button
					text="Ask Question"
					callback={
						isLogin
							? () => navigate('/questions/ask')
							: () => navigate('/login')
					}
				/>
			</Section>
			<Section>{Detail}</Section>
			<BottomSection filter={filter}>
				<div className="questions">
					{questionCount && `${questionCount} questions`}
				</div>
				<TabContainer>
					<Tab tabList={tabList} tab={tab} setTab={setTab} />
					&nbsp;
					{filter && (
						<Button
							text="filter"
							color={`hsl(205,47%,42%)`}
							background={`hsl(205,46%,92%)`}
							borderColor={`hsl(205,41%,63%)`}
							shadowPersent={'70%'}
						/>
					)}
				</TabContainer>
			</BottomSection>
		</ListHeaderStyle>
	);
};

export default ListHeader;
