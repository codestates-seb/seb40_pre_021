import styled from 'styled-components';
import Button from '../common/Button';
import Tab from '../common/Tab';

const ListHeaderStyle = styled.div`
	padding: 24px;

	section {
		padding: 12px;
		display: flex;
		align-items: center;
	}
`;

const Section = styled.section`
	justify-content: space-between;

	.title {
		font-size: 32px;
		font-weight: 500;
	}
`;
const BottomSection = styled.section`
	justify-content: ${(props) => (props.filter ? 'space-between' : 'flex-end')};

	.questions {
		font-size: 1.30769231rem;
		font-weight: 500;
	}
`;

const TabContainer = styled.div`
	display: flex;
`;

const ListHeader = ({ title, Detail, filter, tabList, questionCount }) => {
	return (
		<ListHeaderStyle>
			<Section>
				<div className="title">{title}</div>
				<Button text="Ask Question" />
			</Section>
			<Section>{Detail}</Section>
			<BottomSection filter={filter}>
				<div className="questions">
					{questionCount && `${questionCount} questions`}
				</div>
				<TabContainer>
					<Tab tabList={tabList} />
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
